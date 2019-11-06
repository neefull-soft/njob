package com.xxl.job.executor.service.jobhandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.annotation.XmlElementAnno;
import com.xxl.job.executor.service.jobhandler.config.CFDAConfiguration;
import com.xxl.job.executor.service.jobhandler.service.CfdaService;
import com.xxl.job.executor.service.jobhandler.utils.DateUtil;
import com.xxl.job.executor.service.jobhandler.utils.HttpClientCallSoapUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPMessage;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.*;

import static com.xxl.job.executor.service.jobhandler.utils.HttpClientCallSoapUtil.getResult;

/**
 * 国家总局任务处理器
 */
@Component
@JobHandler("CFDAJobHandler")
public class CFDAShareDataHandler extends IJobHandler {
    @Autowired
    CfdaService cfdaService;

    /**
     * @param param 传进来参数，代表的是数据类型
     * @return 成功或者失败
     * @throws Exception 传递参数约定规则：{tableName:"t_share_gcyp",dateFieldName:"gxsj",dbNameEN:"gcyp",dbNameCN:"国产药品",modelName:"GCYPModel"}
     */
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        try {
            Map<String, Object> paramObject = new HashMap<>();
            /**
             *  解析参数
             */
            JSONObject jsonObject = JSON.parseObject(param);
            //获取最大时间
            String maxDate = cfdaService.getMaxDate(jsonObject.getString("tableName"), jsonObject.getString("dateFieldName"), "%Y-%m-%d");
            XxlJobLogger.log(maxDate);
            // String maxDate = "2018-12-26";
            //获取后一天的时间
            String dateParam = DateUtil.getSpecifiedDayAfter(maxDate);
            final String requestStr = CFDAConfiguration.getAdminConfig().getSoapReqXml(dateParam, jsonObject.getString("dbNameCN"));
            //采用SOAP1.1调用服务端
            SOAPMessage msg = HttpClientCallSoapUtil.formatSoapString(HttpClientCallSoapUtil.doPostSoap1_1(CFDAConfiguration.getAdminConfig().getUrl(), requestStr, ""));
            SOAPBody body = msg.getSOAPBody();
            Iterator<SOAPElement> iterator = body.getChildElements();
            String result = getResult(iterator, "result");
            if (result.contains("发生异常：null")) {
                XxlJobLogger.log(result);
                return ReturnT.FAIL;
            }
            List<Map<String, String>> paramsList = null;

            paramsList = parseXmlToParams(result, "row", jsonObject.getString("modelName"));
            // daService.batchSave("t_share_gcyp",paramsList); 批量插入存在问题，改用单条插入的方式
            for (Map<String, String> item : paramsList) {
                cfdaService.save(jsonObject.getString("tableName"), item);
            }
            XxlJobLogger.log("解析入库数据量：" + paramsList.size());
        } catch (Exception e) {
            XxlJobLogger.log(e.getMessage());
            return ReturnT.FAIL;
        }
        return ReturnT.SUCCESS;
    }

    /**
     * 解析返回数据  形成数据入库参数
     *
     * @param xml
     * @param childTagName
     * @return
     * @throws IOException
     */
    private List<Map<String, String>> parseXmlToParams(String xml, String childTagName, String className) throws IOException, ClassNotFoundException, DocumentException {
        List paramsList = new ArrayList();
        if (xml != null && xml != "") {
            Class clazz = Class.forName(className);
            Field[] fields = clazz.getDeclaredFields();
            List<Field> fieldList = new ArrayList<Field>();
            for (Field fie : fields) {
                if (fie.isAnnotationPresent(XmlElementAnno.class)) {
                    fieldList.add(fie);
                }
            }
                StringReader read = new StringReader(xml);
                InputSource source = new InputSource(read);
                //创建一个新的SAXBuilder
                SAXReader sr = new SAXReader();
                Document doc = sr.read(source);
                //取的根元素
                Element root = doc.getRootElement();
                List<Element> lEls = root.elements(childTagName);
                //循环每个值节点
                for (Element e : lEls) {
                    Map<String, String> valueMap = new HashMap<String, String>();
                    if (!fieldList.isEmpty()) {
                        for (Field field : fieldList) {
                            Element child = e.element(field.getName().toUpperCase());
                            if (child != null) {
                                valueMap.put(field.getName(), child.getStringValue());
                            }
                        }
                    }
                    paramsList.add(valueMap);
                }
        }
        return paramsList;
    }
}

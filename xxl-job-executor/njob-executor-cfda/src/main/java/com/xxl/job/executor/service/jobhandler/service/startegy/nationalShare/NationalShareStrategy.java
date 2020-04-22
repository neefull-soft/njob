package com.xxl.job.executor.service.jobhandler.service.startegy.nationalShare;

import com.alibaba.fastjson.JSONObject;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.executor.service.jobhandler.service.NationalShareService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-08-27 15:33
 **/
public abstract class NationalShareStrategy {
    public abstract ReturnT<String> doAction() throws Exception;

    /**
     * 处理接口返回结果
     * @param nationalShareService
     * @param response
     * @param tableName
     * @param sqId
     */
    public void processResponse(NationalShareService nationalShareService,
                                String response, String tableName, String sqId){
        String flag = "0";
        try {
            Document doc= DocumentHelper.parseText(response);
            // 获取根节点
            Element rootElt = doc.getRootElement();
            String errorcode =rootElt.element("resultType").getText();
            String errormsg = rootElt.element("sysMessage").getText();
            String udate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            if("1".equals(errorcode)){
                flag = "1";
            }else{
                flag = "-1";
            }
            nationalShareService.updateUploadState(tableName, sqId, udate, flag, errormsg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取返回 json 字符串key 对应的内容
     */
    private String getValByKey(String json, String key) {
        try {
            JSONObject jsonStr = (JSONObject) JSONObject.parse(json);
            return jsonStr.getString(key);
        } catch (Exception e) {
            // 返回的不是 json
            return "-1";
        }
    }
}

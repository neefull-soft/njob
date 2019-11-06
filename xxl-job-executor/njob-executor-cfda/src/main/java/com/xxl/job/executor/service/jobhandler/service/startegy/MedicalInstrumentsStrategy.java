package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.alibaba.fastjson.JSONObject;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.executor.service.jobhandler.service.MedicalInstrumentsService;
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
public abstract class MedicalInstrumentsStrategy {
    public abstract ReturnT<String> doAction() throws Exception;

    /**
     * 处理接口返回结果
     * @param medicalInstrumentsService
     * @param response
     * @param tableName
     * @param sqId
     */
    public void processResponse(MedicalInstrumentsService medicalInstrumentsService,
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
            medicalInstrumentsService.updateYlqxUploadState(tableName, sqId, udate, flag, errormsg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 处理接口返回结果-医疗器械生产企业检查
     * @param medicalInstrumentsService
     * @param response
     * @param sqId
     */
    public void processResponseV2(MedicalInstrumentsService medicalInstrumentsService,
                                String response, String sqId){
        String flag = "0";
        String errorcode = this.getValByKey(response, "code");
        String udate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        if("000000".equals(errorcode)){
            flag = "1";
        }else{
            flag = "-1";
        }
        medicalInstrumentsService.updateYlqxJCUploadState(sqId, udate, flag, errorcode);
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
package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.executor.service.jobhandler.config.MedicalDeviceConfiguration;
import com.xxl.job.executor.service.jobhandler.service.MedicalDevicesService;
import com.xxl.job.executor.service.jobhandler.utils.HttpUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class MedicalDeviceStrategy {
    public abstract ReturnT<String> doAction() throws Exception;

    /**
     * 获取Token
     *
     * @return
     * @throws Exception
     */
    public String getToken() throws Exception {
        String url = MedicalDeviceConfiguration.getAdminConfig().getTokenUrl();
        Map<String, String> params = new HashMap<String, String>();
        params.put("client_id", MedicalDeviceConfiguration.getAdminConfig().getClientId());
        params.put("client_secret", MedicalDeviceConfiguration.getAdminConfig().getClientSecret());
        params.put("redirect_uri", MedicalDeviceConfiguration.getAdminConfig().getRediretUrl());
        params.put("grant_type", MedicalDeviceConfiguration.getAdminConfig().getGrantType());
        params.put("state", MedicalDeviceConfiguration.getAdminConfig().getState());
        String result = HttpUtils.sendPost(url, params);
        JSONObject tko = JSONObject.parseObject(result);
        return tko.getString("access_token");
    }

    /**
     * 处理接口返回结果
     *
     * @param medicalDevicesService
     * @param tableName
     * @param result
     * @param sqid
     */
    public void processResponse(MedicalDevicesService medicalDevicesService, String tableName, String result, String sqid) {
        String flag = "0";
        String errAppend = "";
        String errorcode = this.getValByKey(result, "errorcode");
        String errormsg = this.getValByKey(result, "errormsg");
        String udate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        if ("0".equals(errorcode)) {
            flag = "1";
        } else if ("40002".equals(errorcode)) {
            errAppend = "TypeNotAuthorized";
            flag = "-1";
        } else if ("40003".equals(errorcode)) {
            errAppend = "DataRequired";
            flag = "-1";
        } else if ("40004".equals(errorcode)) {
            errAppend = "DataSaveError";
            flag = "-1";
        } else if ("40005".equals(errorcode)) {
            errAppend = "RecordNotExisted";
            flag = "-1";
        } else if ("40006".equals(errorcode)) {
            errAppend = "FatalError";
            flag = "-1";
        } else {
            flag = "-1";
        }
        medicalDevicesService.updateMedicalDeviceData(tableName, sqid, udate, flag, errAppend + ":" + errormsg);

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

    /**
     * Json对象Key转成大写
     *
     * @param json
     * @return
     */
    public String jsonKeyToUPcase(JSONObject json) {
        JSONObject nJson = new JSONObject();
        Set<String> keys = json.keySet();
        for (String key : keys) {
            if ("products".equals(key)) {
                nJson.put("products", json.get(key));
                continue;
            }
            nJson.put(key.toUpperCase(), json.get(key));

        }
        return JSONObject.toJSONString(nJson, SerializerFeature.WriteMapNullValue);
    }
}

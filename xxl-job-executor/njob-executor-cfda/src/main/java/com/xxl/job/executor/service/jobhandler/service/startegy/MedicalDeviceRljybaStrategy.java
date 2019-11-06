package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.config.MedicalDeviceConfiguration;
import com.xxl.job.executor.service.jobhandler.config.MedicalDevicesType;
import com.xxl.job.executor.service.jobhandler.model.MedicalDeviceRlba;
import com.xxl.job.executor.service.jobhandler.service.MedicalDevicesService;
import com.xxl.job.executor.service.jobhandler.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 二类器械经营备案
 */
@Component
public class MedicalDeviceRljybaStrategy extends MedicalDeviceStrategy {
    @Autowired
    MedicalDevicesService medicalDevicesService;

    @Override
    public ReturnT<String> doAction() throws Exception {
        //获取数据
        String tableName = MedicalDevicesType.MEDICAL_DEVICES_TYPE_RLJYBA.getName();
        String type = null;
        String data = null;
        String postUrl = null;
        List<MedicalDeviceRlba> dataList = medicalDevicesService.getMedicalDeviceDataRLJYBA(tableName);
        if (dataList.size() == 0) {
            XxlJobLogger.log("二类经营备案新增数据为0");
            return ReturnT.SUCCESS;
        }
        String token = getToken();
        Map<String, String> params = new HashMap<>();
        //开始解析数据，封装数据文件
        for (MedicalDeviceRlba rlba : dataList) {
            String sqsxdm = rlba.getSqsxdm().trim();
            String sqid = rlba.getSqid().trim();
            JSONObject jrlba = (JSONObject) JSONObject.toJSON(rlba);
            jrlba.remove("sqid");
            jrlba.remove("sqsxdm");
            //新增加
            if (sqsxdm.equals(MedicalDevicesType.RLJYBA.NEW.getName())) {
                type = "JYBA";
                postUrl = MedicalDeviceConfiguration.getAdminConfig().getDataUrl() + type + ".json?access_token=" + token;
                data = jsonKeyToUPcase(jrlba);

            } else if (sqsxdm.equals(MedicalDevicesType.RLJYBA.BG.getName())) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("BH", jrlba.get("bh"));
                jsonObject.put("QYMCBGH", jrlba.get("qymc"));
                jsonObject.put("JYFSBGH", jrlba.get("jyfs"));
                jsonObject.put("FDDBRBGH", jrlba.get("fddbr"));
                jsonObject.put("QYFZRBGH", jrlba.get("qyfzr"));
                jsonObject.put("ZSBGH", jrlba.get("zs"));
                jsonObject.put("JYCSBGH", jrlba.get("jycs"));
                jsonObject.put("JYFWBGH", jrlba.get("jyfw"));
                jsonObject.put("KFDZBGH", jrlba.get("kfdz"));
                jsonObject.put("XYDM", jrlba.get("xydm"));
                jsonObject.put("FZRQ", jrlba.get("fzrq"));
                jsonObject.put("JYFW_2017_BGH", jrlba.get("jyfw_2017"));
                type = "JYBABG";
                postUrl = MedicalDeviceConfiguration.getAdminConfig().getDataUrl() + type + ".json?access_token=" + token;
                data = JSONObject.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue);

            } else if (sqsxdm.equals(MedicalDevicesType.RLJYBA.BF.getName())) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("BH", jrlba.get("bh"));
                jsonObject.put("XYDM", jrlba.get("xydm"));
                jsonObject.put("FZRQ", jrlba.get("fzrq"));
                type = "JYBABF";
                postUrl = MedicalDeviceConfiguration.getAdminConfig().getDataUrl() + type + ".json?access_token=" + token;
                data = JSONObject.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue);
            } else if (sqsxdm.equals(MedicalDevicesType.RLJYBA.ZX.getName())) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("BH", jrlba.get("bh"));
                jsonObject.put("FZRQ", jrlba.get("fzrq"));
                type = "JYBABZ";
                postUrl = MedicalDeviceConfiguration.getAdminConfig().getDataUrl() + type + ".json?access_token=" + token;
                data = JSONObject.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue);
                ;
            }
            params.put("type", type);
            params.put("data", data);
            postUrl = MedicalDeviceConfiguration.getAdminConfig().getDataUrl() + type + ".json?access_token=" + token;
            String response = HttpUtils.sendPost(postUrl, params);
            processResponse(medicalDevicesService, tableName, response, sqid);
        }
        return ReturnT.SUCCESS;
    }
}

package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.config.MedicalDeviceConfiguration;
import com.xxl.job.executor.service.jobhandler.config.MedicalDevicesType;
import com.xxl.job.executor.service.jobhandler.model.MedicalDeviceJyxk;
import com.xxl.job.executor.service.jobhandler.service.MedicalDevicesService;
import com.xxl.job.executor.service.jobhandler.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 医疗器械经营许可
 */
@Component
public class MedicalDeviceJyxkStrategy extends MedicalDeviceStrategy {
    @Autowired
    MedicalDevicesService medicalDevicesService;

    @Override
    public ReturnT<String> doAction() throws Exception {
        //获取数据
        String tableName = MedicalDevicesType.MEDICAL_DEVICES_TYPE_JYXK.getName();
        String type = null;
        String data = null;
        String postUrl = null;
        List<MedicalDeviceJyxk> dataList = medicalDevicesService.getMedicalDeviceDataJYXK(tableName);
        if (dataList.size() == 0) {
            XxlJobLogger.log("医疗器械经营许可新增数据为0");
            return ReturnT.SUCCESS;
        }
        String token = getToken();
        Map<String, String> params = new HashMap<>();
        //开始解析数据，封装数据文件
        for (MedicalDeviceJyxk jyxk : dataList) {
            String sqsxdm = jyxk.getSqsxdm().trim();
            String sqid = jyxk.getSqid().trim();
            JSONObject jrlba = (JSONObject) JSONObject.toJSON(jyxk);
            jrlba.remove("sqid");
            jrlba.remove("sqsxdm");
            //新增加
            if (sqsxdm.equals(MedicalDevicesType.JYXK.NEW.getName()) || "QX0R".equals(sqsxdm)) {
                type = "JYXK";
                postUrl = MedicalDeviceConfiguration.getAdminConfig().getDataUrl() + type + ".json?access_token=" + token;
                data = jsonKeyToUPcase(jrlba);

            } else if (sqsxdm.equals(MedicalDevicesType.JYXK.BG.getName()) || "QX0S".equals(sqsxdm)) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("BH", jrlba.get("bh"));
                jsonObject.put("QYMCBGH", jrlba.get("qymc"));
                jsonObject.put("JYFSBGH", jrlba.get("jyfs"));
                jsonObject.put("FDDBRBGH", jrlba.get("fddbr"));
                jsonObject.put("QYFZRBGH", jrlba.get("qyfzr"));
                jsonObject.put("ZSBGH", jrlba.get("zs"));
                jsonObject.put("JYCSBGH", jrlba.get("jycs"));
                jsonObject.put("JYFWBGH", jrlba.get("jyfw"));
                jsonObject.put("JYFW_2017_BGH", jrlba.get("jyfw_2017"));
                jsonObject.put("KFDZBGH", jrlba.get("kfdz"));
                jsonObject.put("XYDM", jrlba.get("xydm"));
                jsonObject.put("FZRQ", jrlba.get("fzrq"));
                type = "JYXKBG";
                postUrl = MedicalDeviceConfiguration.getAdminConfig().getDataUrl() + type + ".json?access_token=" + token;
                data = JSONObject.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue);

            } else if (sqsxdm.equals(MedicalDevicesType.JYXK.YX.getName()) || "QX0U".equals(sqsxdm)) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("BH", jrlba.get("bh"));
                jsonObject.put("XYDM", jrlba.get("xydm"));
                jsonObject.put("FZRQ", jrlba.get("fzrq"));
                jsonObject.put("YXQX", jrlba.get("yxqx"));
                type = "JYXKYX";
                postUrl = MedicalDeviceConfiguration.getAdminConfig().getDataUrl() + type + ".json?access_token=" + token;
                data = JSONObject.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue);
            } else if (sqsxdm.equals(MedicalDevicesType.JYXK.BF.getName())) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("BH", jrlba.get("bh"));
                jsonObject.put("XYDM", jrlba.get("xydm"));
                jsonObject.put("FZRQ", jrlba.get("fzrq"));
                type = "JYXKBF";
                postUrl = MedicalDeviceConfiguration.getAdminConfig().getDataUrl() + type + ".json?access_token=" + token;
                data = JSONObject.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue);
                ;
            } else if (sqsxdm.equals(MedicalDevicesType.JYXK.ZX.getName())) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("BH", jrlba.get("bh"));
                jsonObject.put("XYDM", jrlba.get("xydm"));
                jsonObject.put("FZRQ", jrlba.get("fzrq"));
                type = "JYXKZX";
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

package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.config.MedicalDeviceConfiguration;
import com.xxl.job.executor.service.jobhandler.config.MedicalDevicesType;
import com.xxl.job.executor.service.jobhandler.model.EquipmentProductsYlscba;
import com.xxl.job.executor.service.jobhandler.model.MedicalDeviceYlscba;
import com.xxl.job.executor.service.jobhandler.service.MedicalDevicesService;
import com.xxl.job.executor.service.jobhandler.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一类生产备案
 */
@Component
public class MedicalDeviceYlscbaStrategy extends MedicalDeviceStrategy {
    @Autowired
    MedicalDevicesService medicalDevicesService;

    @Override
    public ReturnT<String> doAction() throws Exception {
        //获取数据
        String tableName = MedicalDevicesType.MEDICAL_DEVICES_TYPE_YLSCBA.getName();
        String type = null;
        String data = null;
        String postUrl = null;
        List<MedicalDeviceYlscba> dataList = medicalDevicesService.getMedicalDeviceDatYLSCBA(tableName);
        if (dataList.size() == 0) {
            XxlJobLogger.log("医疗器械I类生产备案新增数据为0");
            return ReturnT.SUCCESS;
        }
        String token = getToken();
        Map<String, String> params = new HashMap<>();
        //开始解析数据，封装数据文件
        for (MedicalDeviceYlscba ylscba : dataList) {
            String sqsxdm = ylscba.getSqsxdm().trim();
            String sqid = ylscba.getSqid().trim();
            JSONObject jscba = (JSONObject) JSONObject.toJSON(ylscba);
            //移除非必要字段
            jscba.remove("sqid");
            jscba.remove("sqsxdm");
            //新增加
            if (sqsxdm.equals(MedicalDevicesType.YLSCBA.NEW.getName())) {
                List<EquipmentProductsYlscba> cps = medicalDevicesService.getEquipmentProductsYLSCBA(sqid);
                List<JSONObject> listCpba = new ArrayList<JSONObject>();
                for (EquipmentProductsYlscba cp : cps) {
                    JSONObject jsonCpba = new JSONObject();
                    jsonCpba.put("CPMC", cp.getCpmc());
                    jsonCpba.put("CPH", cp.getCph());
                    jsonCpba.put("SFSTSC", cp.getSfstsc());
                    jsonCpba.put("WTQX", cp.getWtqx());
                    jsonCpba.put("DZRQ", cp.getDzrq());
                    listCpba.add(jsonCpba);
                }
                jscba.put("products", listCpba);
                type = "SCBA";
                postUrl = MedicalDeviceConfiguration.getAdminConfig().getDataUrl() + type + ".json?access_token=" + token;
                data = jsonKeyToUPcase(jscba);

            } else if (sqsxdm.equals(MedicalDevicesType.YLSCBA.BG.getName())) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("BH", jscba.get("bh"));
                jsonObject.put("QYMCBGH", jscba.get("qymc"));
                jsonObject.put("ZSBGH", jscba.get("zs"));
                jsonObject.put("FDDBRBGH", jscba.get("fddbr"));
                jsonObject.put("QYFZRBGH", jscba.get("qyfzr"));
                jsonObject.put("SCDZFWZBG", jscba.get(""));
                jsonObject.put("SCDZWZBG", jscba.get("qyfzr"));
                jsonObject.put("SCFWBGH", jscba.get("scfw"));
                jsonObject.put("XYDM", jscba.get("xydm"));
                jsonObject.put("FZRQ", jscba.get("fzrq"));
                //封装产品数据
                List<EquipmentProductsYlscba> cps = medicalDevicesService.getEquipmentProductsYLSCBA(sqid);
                List<JSONObject> listCpba = new ArrayList<JSONObject>();
                for (EquipmentProductsYlscba cp : cps) {
                    String zt = cp.getZt().trim();
                    JSONObject jsonCpba = new JSONObject();
                    jsonCpba.put("CPMC", cp.getCpmc());
                    jsonCpba.put("CPH", cp.getCph());
                    jsonCpba.put("SFSTSC", cp.getSfstsc());
                    jsonCpba.put("WTQX", cp.getWtqx());
                    if ("0".equals(zt)) {
                        jsonCpba.put("ZT", "add");
                    } else if ("1".equals(zt)) {
                        jsonCpba.put("ZT", "remove");
                    } else {
                        continue;
                    }
                    listCpba.add(jsonCpba);
                }
                jscba.put("products", listCpba);
                type = "SCBABG";
                postUrl = MedicalDeviceConfiguration.getAdminConfig().getDataUrl() + type + ".json?access_token=" + token;
                data = JSONObject.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue);

            } else if (sqsxdm.equals(MedicalDevicesType.YLSCBA.BF.getName())) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("BH", jscba.get("bh"));
                jsonObject.put("XYDM", jscba.get("xydm"));
                jsonObject.put("FZRQ", jscba.get("fzrq"));
                type = "SCBABF";
                postUrl = MedicalDeviceConfiguration.getAdminConfig().getDataUrl() + type + ".json?access_token=" + token;
                data = JSONObject.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue);
                ;
            } else if (sqsxdm.equals(MedicalDevicesType.YLSCBA.ZX.getName())) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("BH", jscba.get("bh"));
                jsonObject.put("FZRQ", jscba.get("fzrq"));
                type = "SCBABZ";
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

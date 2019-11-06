package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.config.MedicalDeviceConfiguration;
import com.xxl.job.executor.service.jobhandler.config.MedicalDevicesType;
import com.xxl.job.executor.service.jobhandler.model.EquipmentProductsScxk;
import com.xxl.job.executor.service.jobhandler.model.MedicalDeviceScxk;
import com.xxl.job.executor.service.jobhandler.service.MedicalDevicesService;
import com.xxl.job.executor.service.jobhandler.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 二、三类医疗器械生产许可，市局业务
 */
@Component
public class MedicalDeviceScxkStrategy extends MedicalDeviceStrategy {
    @Autowired
    MedicalDevicesService medicalDevicesService;

    @Override
    public ReturnT<String> doAction() throws Exception {
        //获取数据
        String tableName = MedicalDevicesType.MEDICAL_DEVICES_TYPE_SCXK.getName();
        String type = null;
        String data = null;
        String postUrl = null;
        List<MedicalDeviceScxk> dataList = medicalDevicesService.getMedicalDeviceDatSCXK(tableName);
        if (dataList.size() == 0) {
            XxlJobLogger.log(" 第二、三类医疗器械生产许可：0");
            return ReturnT.SUCCESS;
        }
        String token = getToken();
        Map<String, String> params = new HashMap<>();
        //开始解析数据，封装数据文件
        for (MedicalDeviceScxk scxk : dataList) {
            String sqsxdm = scxk.getSqsxdm().trim();
            String sqid = scxk.getSqid().trim();
            JSONObject jscba = (JSONObject) JSONObject.toJSON(scxk);
            //移除非必要字段
            jscba.remove("sqid");
            jscba.remove("sqsxdm");
            //新增加
            if (sqsxdm.equals(MedicalDevicesType.SCXK.NEW.getName())) {
                List<EquipmentProductsScxk> cps = medicalDevicesService.getEquipmentProductsSCXK(sqid);
                List<JSONObject> listScxk = new ArrayList<JSONObject>();
                for (EquipmentProductsScxk cp : cps) {
                    JSONObject jsonCpba = new JSONObject();
                    jsonCpba.put("CPMC", cp.getCpmc());
                    jsonCpba.put("CPH", cp.getCph());
                    jsonCpba.put("SFSTSC", cp.getSfstsc());
                    jsonCpba.put("WTQX", cp.getWtqx());
                    jsonCpba.put("DZRQ", cp.getDzrq());
                    jsonCpba.put("CPLB", cp.getCplb());
                    listScxk.add(jsonCpba);
                }
                jscba.put("products", listScxk);
                type = "SCXK";
                postUrl = MedicalDeviceConfiguration.getAdminConfig().getDataUrl() + type + ".json?access_token=" + token;
                data = jsonKeyToUPcase(jscba);

            } else if (sqsxdm.equals(MedicalDevicesType.SCXK.BG.getName())) {
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
                List<EquipmentProductsScxk> cps = medicalDevicesService.getEquipmentProductsSCXK(sqid);
                List<JSONObject> listCpba = new ArrayList<JSONObject>();
                for (EquipmentProductsScxk cp : cps) {
                    String zt = cp.getZt().trim();
                    JSONObject jsonCpba = new JSONObject();
                    jsonCpba.put("CPMC", cp.getCpmc());
                    jsonCpba.put("CPH", cp.getCph());
                    jsonCpba.put("SFSTSC", cp.getSfstsc());
                    jsonCpba.put("WTQX", cp.getWtqx());
                    jsonCpba.put("CPLB", cp.getCplb());
                    jsonCpba.put("DZRQ", cp.getDzrq());
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
                type = "SCXKBG";
                postUrl = MedicalDeviceConfiguration.getAdminConfig().getDataUrl() + type + ".json?access_token=" + token;
                data = JSONObject.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue);

            } else if (sqsxdm.equals(MedicalDevicesType.SCXK.YX.getName())) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("BH", jscba.get("bh"));
                jsonObject.put("XYDM", jscba.get("xydm"));
                jsonObject.put("FZRQ", jscba.get("fzrq"));
                jsonObject.put("YXQX", jscba.get("yxqx"));
                type = "SCXKYX";
                postUrl = MedicalDeviceConfiguration.getAdminConfig().getDataUrl() + type + ".json?access_token=" + token;
                data = JSONObject.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue);
                ;
            } else if (sqsxdm.equals(MedicalDevicesType.SCXK.BF.getName())) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("BH", jscba.get("bh"));
                jsonObject.put("FZRQ", jscba.get("fzrq"));
                jsonObject.put("XYDM", jscba.get("xydm"));
                type = "SCXKBF";
                postUrl = MedicalDeviceConfiguration.getAdminConfig().getDataUrl() + type + ".json?access_token=" + token;
                data = JSONObject.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue);
                ;
            }else if (sqsxdm.equals(MedicalDevicesType.SCXK.ZX.getName())) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("BH", jscba.get("bh"));
                jsonObject.put("FZRQ", jscba.get("fzrq"));
                jsonObject.put("XYDM", jscba.get("xydm"));
                type = "SCXKZX";
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

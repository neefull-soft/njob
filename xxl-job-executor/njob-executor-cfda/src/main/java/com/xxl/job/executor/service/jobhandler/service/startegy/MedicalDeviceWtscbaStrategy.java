package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.alibaba.fastjson.JSONObject;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.config.MedicalDeviceConfiguration;
import com.xxl.job.executor.service.jobhandler.config.MedicalDevicesType;
import com.xxl.job.executor.service.jobhandler.model.EquipmentProductsWtscba;
import com.xxl.job.executor.service.jobhandler.model.MedicalDeviceWtscba;
import com.xxl.job.executor.service.jobhandler.service.MedicalDevicesService;
import com.xxl.job.executor.service.jobhandler.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 委托生产备案
 */
@Component
public class MedicalDeviceWtscbaStrategy extends MedicalDeviceStrategy {
    @Autowired
    MedicalDevicesService medicalDevicesService;

    @Override
    public ReturnT<String> doAction() throws Exception {
        //获取数据
        String tableName = MedicalDevicesType.MEDICAL_DEVICES_TYPE_WTSCBA.getName();
        String type = null;
        String data = null;
        String postUrl = null;
        List<MedicalDeviceWtscba> dataList = medicalDevicesService.getMedicalDeviceDatWTSCBA(tableName);
        if (dataList.size() == 0) {
            XxlJobLogger.log(" 委托生产备案新增数据：0");
            return ReturnT.SUCCESS;
        }
        String token = getToken();
        Map<String, String> params = new HashMap<>();
        //开始解析数据，封装数据文件
        for (MedicalDeviceWtscba wtscba : dataList) {
            String sqid = wtscba.getSqid().trim();
            JSONObject jscba = (JSONObject) JSONObject.toJSON(wtscba);
            //移除非必要字段
            jscba.remove("sqid");
            //新增加
            List<EquipmentProductsWtscba> cps = medicalDevicesService.getEquipmentProductsWTSCBA(sqid);
            List<JSONObject> listWtscba = new ArrayList<JSONObject>();
            for (EquipmentProductsWtscba cp : cps) {
                JSONObject jsonCpba = new JSONObject();
                jsonCpba.put("CPMC", cp.getCpmc());
                jsonCpba.put("CPH", cp.getCph());
                jsonCpba.put("WTQX", cp.getWtqx());
                listWtscba.add(jsonCpba);
            }
            jscba.put("products", listWtscba);
            type = "WTSCBA";
            postUrl = MedicalDeviceConfiguration.getAdminConfig().getDataUrl() + type + ".json?access_token=" + token;
            data = jsonKeyToUPcase(jscba);
            params.put("type", type);
            params.put("data", data);
            postUrl = MedicalDeviceConfiguration.getAdminConfig().getDataUrl() + type + ".json?access_token=" + token;
            String response = HttpUtils.sendPost(postUrl, params);
            processResponse(medicalDevicesService, tableName, response, sqid);

        }
        return ReturnT.SUCCESS;
    }
}


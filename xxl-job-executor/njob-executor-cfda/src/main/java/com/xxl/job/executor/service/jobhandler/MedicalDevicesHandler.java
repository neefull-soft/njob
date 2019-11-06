package com.xxl.job.executor.service.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.service.MedicalDevicesService;
import com.xxl.job.executor.service.jobhandler.service.startegy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 国家总局任务处理器
 */
@Component
@JobHandler("MedicalDevicesHandler")
public class MedicalDevicesHandler extends IJobHandler {
    @Autowired
    MedicalDevicesService medicalDevicesService;
    @Autowired
    MedicalDeviceRljybaStrategy medicalDeviceRljybaStrategy;
    @Autowired
    MedicalDeviceJyxkStrategy medicalDeviceJyxkStrategy;
    @Autowired
    MedicalDeviceYlscbaStrategy medicalDeviceYlscbaStrategy;
    @Autowired
    MedicalDeviceScxkStrategy medicalDeviceScxkStrategy;
    @Autowired
    MedicalDeviceWtscbaStrategy medicalDeviceWtscbaStrategy;

    /**
     * @param param 传进来参数，代表的是数据类型
     * @return 成功或者失败
     * @throws Exception
     */
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        try {
            if (null != param && !"".equals(param)) {
                XxlJobLogger.log("开始处理" + param + "数据");
                if ("生产许可".equals(param)) {
                    new MedicalDevicesContext(medicalDeviceScxkStrategy).doAction();
                } else if ("第一类医疗器械生产备案".equals(param)) {
                    new MedicalDevicesContext(medicalDeviceYlscbaStrategy).doAction();
                } else if ("委托生产备案".equals(param)) {
                    new MedicalDevicesContext(medicalDeviceWtscbaStrategy).doAction();
                } else if ("经营许可".equals(param)) {
                    new MedicalDevicesContext(medicalDeviceJyxkStrategy).doAction();
                } else if ("经营备案".equals(param)) {
                    new MedicalDevicesContext(medicalDeviceRljybaStrategy).doAction();
                }
            }

        } catch (Exception e) {
            XxlJobLogger.log(e.getMessage());
            return ReturnT.FAIL;
        }
        return ReturnT.SUCCESS;
    }
}

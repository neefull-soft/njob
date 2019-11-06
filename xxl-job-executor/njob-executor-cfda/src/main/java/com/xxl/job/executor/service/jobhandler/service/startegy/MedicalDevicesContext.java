package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.xxl.job.core.biz.model.ReturnT;

public class MedicalDevicesContext {

    private MedicalDeviceStrategy medicalDeviceStrategy;

    public MedicalDevicesContext(MedicalDeviceStrategy medicalDeviceStrategy) {
        this.medicalDeviceStrategy = medicalDeviceStrategy;
    }

    public ReturnT<String> doAction() throws Exception {
        return medicalDeviceStrategy.doAction();
    }

}

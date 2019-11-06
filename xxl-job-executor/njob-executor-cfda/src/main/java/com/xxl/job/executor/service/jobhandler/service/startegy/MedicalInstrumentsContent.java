package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.xxl.job.core.biz.model.ReturnT;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-08-27 15:33
 **/
public class MedicalInstrumentsContent {

    private MedicalInstrumentsStrategy medicalInstrumentsStrategy;

    public MedicalInstrumentsContent(MedicalInstrumentsStrategy medicalInstrumentsStrategy){
        this.medicalInstrumentsStrategy = medicalInstrumentsStrategy;
    }

    public ReturnT<String> doAction() throws Exception {
        return medicalInstrumentsStrategy.doAction();
    }
}
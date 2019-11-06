package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.xxl.job.core.biz.model.ReturnT;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-10-09 14:35
 **/
public class AnnualQualityContent {

    private AnnualQualityStrategy annualQualityStrategy;

    public AnnualQualityContent(AnnualQualityStrategy annualQualityStrategy){
        this.annualQualityStrategy = annualQualityStrategy;
    }

    public ReturnT<String> doAction() throws Exception {
        return annualQualityStrategy.doAction();
    }
}
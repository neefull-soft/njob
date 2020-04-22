package com.xxl.job.executor.service.jobhandler.service.startegy.nationalShare;

import com.xxl.job.core.biz.model.ReturnT;

public class NationalShareContent {

    private NationalShareStrategy nationalShareStrategy;

    public NationalShareContent(NationalShareStrategy nationalShareStrategy){
        this.nationalShareStrategy = nationalShareStrategy;
    }

    public ReturnT<String> doAction() throws Exception {
        return nationalShareStrategy.doAction();
    }
}

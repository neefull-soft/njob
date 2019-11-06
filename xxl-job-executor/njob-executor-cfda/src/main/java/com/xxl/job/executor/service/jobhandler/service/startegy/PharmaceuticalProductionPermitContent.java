package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.xxl.job.core.biz.model.ReturnT;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-04-01 15:18
 **/
public class PharmaceuticalProductionPermitContent {

    private PharmaceuticalProductionPermitStrategy pharmaceuticalProductionPermitStrategy;

    public PharmaceuticalProductionPermitContent(PharmaceuticalProductionPermitStrategy pharmaceuticalProductionPermitStrategy){
        this.pharmaceuticalProductionPermitStrategy = pharmaceuticalProductionPermitStrategy;
    }

    public ReturnT<String> doAction() throws Exception {
        return pharmaceuticalProductionPermitStrategy.doAction();
    }
}
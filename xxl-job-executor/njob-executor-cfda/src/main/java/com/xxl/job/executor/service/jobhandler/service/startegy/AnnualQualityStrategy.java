package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.xxl.job.core.biz.model.ReturnT;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-10-09 14:36
 **/
public abstract class AnnualQualityStrategy {
    public abstract ReturnT<String> doAction() throws Exception;
}
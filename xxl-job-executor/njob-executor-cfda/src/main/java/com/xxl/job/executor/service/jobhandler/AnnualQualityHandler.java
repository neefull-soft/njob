package com.xxl.job.executor.service.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.service.startegy.AnnualQualityContent;
import com.xxl.job.executor.service.jobhandler.service.startegy.AnnualQualityJjxxzdStrategy;
import com.xxl.job.executor.service.jobhandler.service.startegy.AnnualQualityZcbgStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 医疗器械生产企业-年度质量体系
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-10-09 14:29
 **/
@Component
@JobHandler("AnnualQualityHandler")
public class AnnualQualityHandler extends IJobHandler {

    @Autowired
    AnnualQualityZcbgStrategy annualQualityZcbgStrategy;
    @Autowired
    AnnualQualityJjxxzdStrategy annualQualityJjxxzdStrategy;

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        try {
            if (null != param && !"".equals(param)) {
                XxlJobLogger.log("开始处理" + param + "数据");
                if ("自查报告".equals(param)) {
                    new AnnualQualityContent(annualQualityZcbgStrategy).doAction();
                }else if ("经济信息字段".equals(param)) {
                    new AnnualQualityContent(annualQualityJjxxzdStrategy).doAction();
                }
            }
        } catch (Exception e) {
            XxlJobLogger.log(e.getMessage());
            return ReturnT.FAIL;
        }
        return ReturnT.SUCCESS;
    }
}
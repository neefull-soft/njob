package com.xxl.job.executor.service.jobhandler.service;

import com.xxl.job.executor.service.jobhandler.model.YlqxscqyNdzltxJjxxzd;
import com.xxl.job.executor.service.jobhandler.model.YlqxscqyNdzltxZcbg;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-10-09 14:33
 **/
public interface AnnualQualityService {

    /**
     * 插入或更新自查报告
     * @param ylqxscqyNdzltxZcbg
     */
    void insertOrUpdateZcbg(YlqxscqyNdzltxZcbg ylqxscqyNdzltxZcbg);

    /**
     * 插入或更新经济信息字段
     * @param ylqxscqyNdzltxJjxxzd
     */
    void insertOrUpdateJjxxzd(YlqxscqyNdzltxJjxxzd ylqxscqyNdzltxJjxxzd);
}
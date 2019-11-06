package com.xxl.job.executor.service.jobhandler.dao;

import com.xxl.job.executor.service.jobhandler.model.YlqxscqyNdzltxJjxxzd;
import com.xxl.job.executor.service.jobhandler.model.YlqxscqyNdzltxZcbg;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-10-09 17:10
 **/
public interface AnnualQualityDao {

    YlqxscqyNdzltxZcbg getZcbgByYearAndOrgCode(YlqxscqyNdzltxZcbg ylqxscqyNdzltxZcbg);

    void updateZcbgLastDate(YlqxscqyNdzltxZcbg ylqxscqyNdzltxZcbg);

    void insertZcbg(YlqxscqyNdzltxZcbg ylqxscqyNdzltxZcbg);

    /**
     * 插入或更新自查报告
     * @param ylqxscqyNdzltxZcbg
     */
    public void insertOrUpdateZcbg(YlqxscqyNdzltxZcbg ylqxscqyNdzltxZcbg);

    /**
     * 插入或更新经济信息字段
     * @param ylqxscqyNdzltxJjxxzd
     */
    public void insertOrUpdateJjxxzd(YlqxscqyNdzltxJjxxzd ylqxscqyNdzltxJjxxzd);
}
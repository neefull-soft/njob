package com.xxl.job.executor.service.jobhandler.service.impl;

import com.xxl.job.executor.service.jobhandler.dao.AnnualQualityDao;
import com.xxl.job.executor.service.jobhandler.model.YlqxscqyNdzltxJjxxzd;
import com.xxl.job.executor.service.jobhandler.model.YlqxscqyNdzltxZcbg;
import com.xxl.job.executor.service.jobhandler.service.AnnualQualityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-10-09 17:08
 **/
@Service
public class AnnualQualityServiceImpl implements AnnualQualityService {

    @Autowired
    AnnualQualityDao annualQualityDao;

    /**
     * 插入或更新自查报告
     * @param ylqxscqyNdzltxZcbg
     */
    @Override
    public void insertOrUpdateZcbg(YlqxscqyNdzltxZcbg ylqxscqyNdzltxZcbg) {
        if(!StringUtils.isEmpty(ylqxscqyNdzltxZcbg.getCompanyName()) && !StringUtils.isEmpty(ylqxscqyNdzltxZcbg.getOrgCode())){
            selectToUpdateOrInsert(ylqxscqyNdzltxZcbg);
        }
    }

    /**
     * 插入或更新经济信息字段
     * @param ylqxscqyNdzltxJjxxzd
     */
    @Override
    public void insertOrUpdateJjxxzd(YlqxscqyNdzltxJjxxzd ylqxscqyNdzltxJjxxzd) {
        if(!StringUtils.isEmpty(ylqxscqyNdzltxJjxxzd.getCompanyName()) && !StringUtils.isEmpty(ylqxscqyNdzltxJjxxzd.getOrgCode())){
            annualQualityDao.insertOrUpdateJjxxzd(ylqxscqyNdzltxJjxxzd);
        }
    }

    public void selectToUpdateOrInsert(YlqxscqyNdzltxZcbg ylqxscqyNdzltxZcbg) {
        YlqxscqyNdzltxZcbg zcbg = annualQualityDao.getZcbgByYearAndOrgCode(ylqxscqyNdzltxZcbg);
        if(zcbg != null){
            annualQualityDao.updateZcbgLastDate(ylqxscqyNdzltxZcbg);
        }else{
            annualQualityDao.insertZcbg(ylqxscqyNdzltxZcbg);
        }
    }
}
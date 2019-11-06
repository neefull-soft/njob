package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.alibaba.fastjson.JSONObject;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.config.AnnualQualityConfiguration;
import com.xxl.job.executor.service.jobhandler.model.YlqxscqyNdzltxJjxxzd;
import com.xxl.job.executor.service.jobhandler.service.AnnualQualityService;
import com.xxl.job.executor.service.jobhandler.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-10-09 14:57
 **/
@Component
public class AnnualQualityJjxxzdStrategy extends AnnualQualityStrategy {

    private static final Logger LOGGER = Logger.getLogger(AnnualQualityJjxxzdStrategy.class);

    @Autowired
    AnnualQualityService annualQualityService;

    @Override
    public ReturnT<String> doAction() throws Exception {
        try {
            String url = AnnualQualityConfiguration.getAdminConfig().getNdzltxJjxxzdUrl();
            XxlJobLogger.log("请求地址:"+url);
            //调接口获取数据
            String result = HttpUtils.sendGet(url);
            if(!StringUtils.isEmpty(result)){
                JSONObject resultJson = (JSONObject) JSONObject.parse(result);
                if("1".equals(resultJson.getString("Code"))){
                    List<YlqxscqyNdzltxJjxxzd> lstJjxxzd = new ArrayList<YlqxscqyNdzltxJjxxzd>();
                    lstJjxxzd = JSONObject.parseArray(resultJson.getString("Data"), YlqxscqyNdzltxJjxxzd.class);
                    if(lstJjxxzd != null && lstJjxxzd.size() > 0){
                        for(int i=0;i<lstJjxxzd.size();i++){
                            annualQualityService.insertOrUpdateJjxxzd(lstJjxxzd.get(i));
                        }
                    }
                }
            }

            XxlJobLogger.log("医疗器械生产企业-年度质量体系-经济信息字段执行了！！");
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            XxlJobLogger.log(e.getMessage());
            return ReturnT.FAIL;
        }
    }
}
package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.alibaba.fastjson.JSONObject;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.config.AnnualQualityConfiguration;
import com.xxl.job.executor.service.jobhandler.model.YlqxscqyNdzltxZcbg;
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
public class AnnualQualityZcbgStrategy extends AnnualQualityStrategy {

    private static final Logger LOGGER = Logger.getLogger(AnnualQualityZcbgStrategy.class);

    @Autowired
    AnnualQualityService annualQualityService;

    @Override
    public ReturnT<String> doAction() throws Exception {
        try {
            String url = AnnualQualityConfiguration.getAdminConfig().getNdzltxZcbgUrl();
            XxlJobLogger.log("请求地址:"+url);
            String pdfFileUrl = AnnualQualityConfiguration.getAdminConfig().getZcbgPdfFileUrl();
            //调接口获取数据
            String result = HttpUtils.sendGet(url);
            if(!StringUtils.isEmpty(result)){
                JSONObject resultJson = (JSONObject) JSONObject.parse(result);
                if("1".equals(resultJson.getString("Code"))){
                    List<YlqxscqyNdzltxZcbg> lstZcbg = new ArrayList<YlqxscqyNdzltxZcbg>();
                    lstZcbg = JSONObject.parseArray(resultJson.getString("Data"), YlqxscqyNdzltxZcbg.class);
                    if(lstZcbg != null && lstZcbg.size() > 0){
                        for(int i=0;i<lstZcbg.size();i++){
                            if(!StringUtils.isEmpty(lstZcbg.get(i).getElevenPath())){
                                lstZcbg.get(i).setElevenPath(lstZcbg.get(i).getElevenPath().replaceAll("~",pdfFileUrl));
                            }
                            if(!StringUtils.isEmpty(lstZcbg.get(i).getTwoPath())){
                                lstZcbg.get(i).setTwoPath(lstZcbg.get(i).getTwoPath().replaceAll("~",pdfFileUrl));
                            }
                            if(!StringUtils.isEmpty(lstZcbg.get(i).getFourOnePath())){
                                lstZcbg.get(i).setFourOnePath(lstZcbg.get(i).getFourOnePath().replaceAll("~",pdfFileUrl));
                            }
                            if(!StringUtils.isEmpty(lstZcbg.get(i).getFourTwoPath())){
                                lstZcbg.get(i).setFourTwoPath(lstZcbg.get(i).getFourTwoPath().replaceAll("~",pdfFileUrl));
                            }
                            if(!StringUtils.isEmpty(lstZcbg.get(i).getNinePath())){
                                lstZcbg.get(i).setNinePath(lstZcbg.get(i).getNinePath().replaceAll("~",pdfFileUrl));
                            }
                            if(!StringUtils.isEmpty(lstZcbg.get(i).getOtherPath())){
                                lstZcbg.get(i).setOtherPath(lstZcbg.get(i).getOtherPath().replaceAll("~",pdfFileUrl));
                            }
                            annualQualityService.insertOrUpdateZcbg(lstZcbg.get(i));
                        }
                    }
                }else{
                    XxlJobLogger.log("年度质量体系-自查报告任务获取接口数据失败！！");
                }
            }

            XxlJobLogger.log("医疗器械生产企业-年度质量体系-自查报告任务执行了！！");
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            XxlJobLogger.log(e.getMessage());
            return ReturnT.FAIL;
        }
    }
}
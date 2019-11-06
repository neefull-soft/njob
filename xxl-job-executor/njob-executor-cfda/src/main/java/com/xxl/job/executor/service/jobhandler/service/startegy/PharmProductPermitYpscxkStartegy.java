package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.config.PharmProductPermitConfiguration;
import com.xxl.job.executor.service.jobhandler.config.PharmProductPermitType;
import com.xxl.job.executor.service.jobhandler.model.PharmPduPmtYpscxkGmp;
import com.xxl.job.executor.service.jobhandler.model.PharmPduPmtYpscxkQyxk;
import com.xxl.job.executor.service.jobhandler.model.PharmPduPmtYpscxkscfw;
import com.xxl.job.executor.service.jobhandler.service.PharmaceuticalProductionPermitService;
import com.xxl.job.executor.service.jobhandler.utils.HttpUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 药品生产许可
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-04-01 15:28
 **/
@Component
public class PharmProductPermitYpscxkStartegy extends PharmaceuticalProductionPermitStrategy {

    private static final Logger LOGGER = Logger.getLogger(PharmProductPermitYpscxkStartegy.class);

    @Autowired
    PharmaceuticalProductionPermitService pharmaceuticalProductionPermitService;

    @Override
    public ReturnT<String> doAction(){
        try {
            //先获取数据
            List<PharmPduPmtYpscxkQyxk> lstYpscxk = pharmaceuticalProductionPermitService.getYpscxkAllDate();
            //记录sqid
            String sqId = "";
            if(lstYpscxk == null || lstYpscxk.size() == 0){
                XxlJobLogger.log("药品生产许可新办换发新增数据为0");
                return ReturnT.SUCCESS;
            }else{
                XxlJobLogger.log("药品生产许可新办换发新增数据为"+lstYpscxk.size());
            }

            String postUrl = PharmProductPermitConfiguration.getAdminConfig().getYpscxkUrl();  // 请求路径
            XxlJobLogger.log("请求路径:"+postUrl);

            List<PharmPduPmtYpscxkQyxk> lstParam = new ArrayList<PharmPduPmtYpscxkQyxk>();   // 每次存放的列表
            PharmPduPmtYpscxkQyxk entYpscxk = null;
            JSONObject obj = new JSONObject();   //每次存放的json实体
            String response = "";   //请求返回的结果
            String areaName = "";   // 区县

            for(int i=0;i<lstYpscxk.size();i++){
                entYpscxk = new PharmPduPmtYpscxkQyxk();
                entYpscxk = pharmaceuticalProductionPermitService.getYpscxkAllDateOne(lstYpscxk.get(i).getCompId());
                if(entYpscxk == null){
                    entYpscxk = pharmaceuticalProductionPermitService.getYpscQyxkz(lstYpscxk.get(i).getCompId());
                    if(!"无".equals(entYpscxk.getCityAreaName())){
                        if("浦东".equals(entYpscxk.getCityAreaName())){
                            entYpscxk.setCityAreaName(entYpscxk.getCityAreaName()+"新区");
                        }else{
                            entYpscxk.setCityAreaName(entYpscxk.getCityAreaName()+"区");
                        }
                    }
                    if(!"无".equals(entYpscxk.getProvinceAreaName())){
                        entYpscxk.setProvinceAreaName(entYpscxk.getProvinceAreaName()+"市");
                    }
                    List<PharmPduPmtYpscxkscfw> lstFw = pharmaceuticalProductionPermitService.getYpscJbsctjdscfw(lstYpscxk.get(i).getCompId());
                    List<PharmPduPmtYpscxkGmp> lstGmp = pharmaceuticalProductionPermitService.getYpscTgjwypgmprz(lstYpscxk.get(i).getCompId());
                    entYpscxk.setLicPSAddrList(lstFw);
                    entYpscxk.setLicGWGmpList(lstGmp);
                }else{
                    if(!"无".equals(entYpscxk.getCityAreaName())){
                        if("浦东".equals(entYpscxk.getCityAreaName())){
                            entYpscxk.setCityAreaName(entYpscxk.getCityAreaName()+"新区");
                        }else{
                            entYpscxk.setCityAreaName(entYpscxk.getCityAreaName()+"区");
                        }
                    }
                    if(!"无".equals(entYpscxk.getProvinceAreaName())){
                        entYpscxk.setProvinceAreaName(entYpscxk.getProvinceAreaName()+"市");
                    }
                }

                sqId = entYpscxk.getCompId();
                entYpscxk.setCompId("");

                obj = new JSONObject();
                lstParam = new ArrayList<PharmPduPmtYpscxkQyxk>();

                lstParam.add(entYpscxk);
                obj.put("param",lstParam);
                response = HttpUtils.sendHeaderPost(postUrl, obj.get("param").toString(),
                        PharmProductPermitConfiguration.getAdminConfig().getSendAccount(),
                        PharmProductPermitConfiguration.getAdminConfig().getSendPwd());
                System.out.println("["+entYpscxk.getCompId()+"]请求结果:"+response);
                XxlJobLogger.log("["+entYpscxk.getCompId()+"]请求结果:"+response);
                LOGGER.info("["+entYpscxk.getCompId()+"]请求结果:"+response);
                processResponse(pharmaceuticalProductionPermitService, response,
                        PharmProductPermitType.PHARM_PRODUCT_PERMIT_TYPE_TPSCXK.getName(),sqId);
            }
            System.out.println("药品生产许可新办换发任务执行了！！");
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            XxlJobLogger.log(e.getMessage());
            return ReturnT.FAIL;
        }
    }
}
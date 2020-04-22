package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.config.PharmProductPermitConfiguration;
import com.xxl.job.executor.service.jobhandler.config.PharmProductPermitType;
import com.xxl.job.executor.service.jobhandler.model.PharmPduPmtYpscxkBg;
import com.xxl.job.executor.service.jobhandler.model.PharmPduPmtYpscxkscfw;
import com.xxl.job.executor.service.jobhandler.model.request.PharmPduPmtYpscxkBgQeq;
import com.xxl.job.executor.service.jobhandler.service.PharmaceuticalProductionPermitService;
import com.xxl.job.executor.service.jobhandler.utils.HttpUtils;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-04-04 11:20
 **/
@Component
public class PharmProductPermitYpscxkChangeStartegy extends PharmaceuticalProductionPermitStrategy {

    private static final Logger LOGGER = Logger.getLogger(PharmProductPermitYpscxkChangeStartegy.class);

    @Autowired
    PharmaceuticalProductionPermitService pharmaceuticalProductionPermitService;

    @Override
    public ReturnT<String> doAction() throws Exception {
        //先获取数据
        List<PharmPduPmtYpscxkBg> lstYpscxkBg = pharmaceuticalProductionPermitService.getYpscxkBgAllDate();
        if(lstYpscxkBg == null || lstYpscxkBg.size() == 0){
            XxlJobLogger.log("药品生产许可变更申报新增数据为0");
            return ReturnT.SUCCESS;
        }else{
            XxlJobLogger.log("药品生产许可变更申报新增数据为"+lstYpscxkBg.size());
        }

        String postUrl = PharmProductPermitConfiguration.getAdminConfig().getYpscxkChangeUrl();  // 请求路径
        XxlJobLogger.log("请求路径:"+postUrl);
        String postAccount = PharmProductPermitConfiguration.getAdminConfig().getSendAccount();
        String postPassword = PharmProductPermitConfiguration.getAdminConfig().getSendPwd();
        List<PharmPduPmtYpscxkBgQeq> lstParam = new ArrayList<PharmPduPmtYpscxkBgQeq>();   // 每次存放的列表
        JSONObject obj = new JSONObject();   //每次存放的json实体
        String response = "";   //请求返回的结果

        PharmPduPmtYpscxkBgQeq requestEnt = new PharmPduPmtYpscxkBgQeq();
        for(PharmPduPmtYpscxkBg entYpscBg : lstYpscxkBg){
            requestEnt.setLicenseNoMarker(entYpscBg.getLicenseNoMarker());
            requestEnt.setLicenseNo(entYpscBg.getLicenseNo());
            requestEnt.setCompName(entYpscBg.getCompName());
            requestEnt.setOrgCode(entYpscBg.getOrgCode());
            requestEnt.setRegAddress(entYpscBg.getRegAddress());
            if(!"无".equals(entYpscBg.getProvinceAreaName())){
                requestEnt.setProvinceAreaName(entYpscBg.getProvinceAreaName()+"市");
            }
            if(!"无".equals(entYpscBg.getCityAreaName())){
                if("浦东".equals(entYpscBg.getCityAreaName())){
                    requestEnt.setCityAreaName("浦东新区");
                }else{
                    requestEnt.setCityAreaName(entYpscBg.getCityAreaName()+"区");
                }
            }
            requestEnt.setIssueDate(entYpscBg.getIssueDate());
            requestEnt.setExpiryDate(entYpscBg.getExpiryDate());
            requestEnt.setLicenseOrg(entYpscBg.getLicenseOrg());
            requestEnt.setOrgName(entYpscBg.getOrgName());
            requestEnt.setOrgPerson(entYpscBg.getOrgPerson());
            requestEnt.setIssuer(entYpscBg.getIssuer());
            requestEnt.setLegalRepresentative(entYpscBg.getLegalRepresentative());
            requestEnt.setEnterpriseHead(entYpscBg.getEnterpriseHead());
            requestEnt.setQualityHead(entYpscBg.getQualityHead());
            requestEnt.setTypeCode(entYpscBg.getTypeCode());
            requestEnt.setChangeDate(entYpscBg.getChangeDate());

            List<PharmPduPmtYpscxkscfw> lstScfw = pharmaceuticalProductionPermitService.getYpscJbsctjdscfw(entYpscBg.getSqId());
            if(lstScfw != null && lstScfw.size() > 0){
                entYpscBg.setLicPSAddrList(lstScfw);
            }else{
                entYpscBg.setLicPSAddrList(new ArrayList<PharmPduPmtYpscxkscfw>());
            }

            obj = new JSONObject();
            lstParam = new ArrayList<PharmPduPmtYpscxkBgQeq>();
            lstParam.add(requestEnt);
            obj.put("param",lstParam);
            response = HttpUtils.sendHeaderPost(postUrl, obj.get("param").toString(),postAccount,postPassword);
            XxlJobLogger.log("请求参数为:"+obj.get("param").toString());
            XxlJobLogger.log("用户{"+postAccount+"}密码{"+postPassword+"}["+entYpscBg.getSqId()+"]请求结果:"+response);
            LOGGER.info("用户{"+postAccount+"}密码{"+postPassword+"}["+entYpscBg.getSqId()+"]请求结果:"+response);
            processResponse(pharmaceuticalProductionPermitService, response,
                    PharmProductPermitType.PHARM_PRODUCT_PERMIT_TYPE_TPSCXKBG.getName(), entYpscBg.getSqId());
        }
        System.out.println("药品生产许可变更申报任务执行了！！");
        return ReturnT.SUCCESS;
    }
}
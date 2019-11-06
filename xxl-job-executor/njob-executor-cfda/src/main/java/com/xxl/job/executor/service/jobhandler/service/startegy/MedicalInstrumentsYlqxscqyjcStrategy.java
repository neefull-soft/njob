package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.config.MedicalInstrumentsConfiguration;
import com.xxl.job.executor.service.jobhandler.config.MedicalInstrumentsType;
import com.xxl.job.executor.service.jobhandler.config.PharmProductPermitType;
import com.xxl.job.executor.service.jobhandler.model.MedInstruYlqxjcJccpxx;
import com.xxl.job.executor.service.jobhandler.model.MedInstruYlqxjcJcx;
import com.xxl.job.executor.service.jobhandler.model.MedInstruYlqxjcJcz;
import com.xxl.job.executor.service.jobhandler.model.MedInstruYlqxjcRwsjbxx;
import com.xxl.job.executor.service.jobhandler.service.MedicalInstrumentsService;
import com.xxl.job.executor.service.jobhandler.utils.CallXmlUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 医疗器械生产企业检查
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-08-30 14:07
 **/
@Component
public class MedicalInstrumentsYlqxscqyjcStrategy extends MedicalInstrumentsStrategy{

    private static final Logger LOGGER = Logger.getLogger(MedicalInstrumentsYlqxjyxkStrategy.class);

    @Autowired
    MedicalInstrumentsService medicalInstrumentsService;

    @Override
    public ReturnT<String> doAction() throws Exception {
        try {
            //先获取数据
            List<MedInstruYlqxjcRwsjbxx> lstRwsjbxx = medicalInstrumentsService.getYlqxjcRwsjbxxLst();
            if(lstRwsjbxx == null || lstRwsjbxx.size() == 0){
                XxlJobLogger.log("医疗器械生产企业检查新增数据为0");
                return ReturnT.SUCCESS;
            }else{
                XxlJobLogger.log("医疗器械生产企业检查新增数据为:"+lstRwsjbxx.size());
            }

            String response = "";  //请求返回的结果
            String url = MedicalInstrumentsConfiguration.getAdminConfig().getYlqxscqyjcUrl();
            String loginid = MedicalInstrumentsConfiguration.getAdminConfig().getLoginid();
            String password = MedicalInstrumentsConfiguration.getAdminConfig().getScqyjcPassword();
            String clientKeystore = MedicalInstrumentsConfiguration.getAdminConfig().getClientKeystorePath();
            XxlJobLogger.log("请求地址:"+url);

            JSONObject obj = new JSONObject();   //每次存放的json实体
            for(MedInstruYlqxjcRwsjbxx rwsjbxx : lstRwsjbxx){
                if(StringUtils.isEmpty(rwsjbxx.getQRJCJGSJ())){
                    rwsjbxx.setQRJCJGSJ(rwsjbxx.getXCJCSJZ());
                }

                obj = new JSONObject();
                obj.put("loginid",loginid);
                obj.put("password",password);
                obj.put("rwsjbxx",rwsjbxx);

                //检查产品信息
                List<MedInstruYlqxjcJccpxx> lstJccpxx = medicalInstrumentsService.getYlqxjcJccpxxByRwsbh(rwsjbxx.getRWSBH());
                if(lstJccpxx != null && lstJccpxx.size() > 0){
                    obj.put("jccpxx",lstJccpxx);
                }else{
                    obj.put("jccpxx","[]");
                }

                //任务检查项明细
                List<MedInstruYlqxjcJcx> lstJcx = medicalInstrumentsService.getYlqxjcJcxByRwsbh(rwsjbxx.getRWSBH());
                if(lstJcx != null && lstJcx.size() > 0){
                    obj.put("jcx",lstJcx);
                }else{
                    obj.put("jcx","[]");
                }

                //任务检查组信息
                List<MedInstruYlqxjcJcz> lstJcz = medicalInstrumentsService.getYlqxjcJczByRwsbh(rwsjbxx.getRWSBH());
                if(lstJcz != null && lstJcz.size() > 0){
                    obj.put("jcz",lstJcz);
                }else{
                    obj.put("jcz","[]");
                }

                //请求
                response = CallXmlUtils.sendYlqxscqyjc(url,obj.toString(),clientKeystore);
                XxlJobLogger.log("参数: "+obj.toString());
                XxlJobLogger.log("["+rwsjbxx.getZJ()+"]请求结果:"+response);
                System.out.println("["+rwsjbxx.getZJ()+"]请求结果:"+response);

                processResponseV2(medicalInstrumentsService, response,rwsjbxx.getZJ());
            }

            XxlJobLogger.log("医疗器械生产企业检查任务执行了！！");
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            XxlJobLogger.log(e.getMessage());
            return ReturnT.FAIL;
        }
    }
}
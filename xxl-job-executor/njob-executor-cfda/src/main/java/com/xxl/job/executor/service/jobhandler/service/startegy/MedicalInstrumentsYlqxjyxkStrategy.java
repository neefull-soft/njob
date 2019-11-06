package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.config.MedicalInstrumentsConfiguration;
import com.xxl.job.executor.service.jobhandler.config.MedicalInstrumentsType;
import com.xxl.job.executor.service.jobhandler.model.MedInstruYlqxjyxk;
import com.xxl.job.executor.service.jobhandler.service.MedicalInstrumentsService;
import com.xxl.job.executor.service.jobhandler.utils.CallXmlUtils;
import com.xxl.job.executor.service.jobhandler.utils.Xml4BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 医疗器械经营许可(备案)数据
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-08-29 14:31
 **/
@Component
public class MedicalInstrumentsYlqxjyxkStrategy extends MedicalInstrumentsStrategy{

    private static final Logger LOGGER = Logger.getLogger(MedicalInstrumentsYlqxjyxkStrategy.class);

    @Autowired
    MedicalInstrumentsService medicalInstrumentsService;

    @Override
    public ReturnT<String> doAction() throws Exception {
        try {
            //先获取数据
            List<String> lstYlqxjyxk = medicalInstrumentsService.getUnUploadYlqxSqId(MedicalInstrumentsType.MEDICAL_INSTRUMENTS_TYPE_YLQXJYXK.getName());
            if(lstYlqxjyxk == null || lstYlqxjyxk.size() == 0){
                XxlJobLogger.log("医疗器械经营许可(备案)新增数据为0");
                return ReturnT.SUCCESS;
            }else{
                XxlJobLogger.log("医疗器械经营许可(备案)新增数据为:"+lstYlqxjyxk.size());
            }

            String response = "";  //请求返回的结果
            String url = MedicalInstrumentsConfiguration.getAdminConfig().getYlqxscxkUrl();
            XxlJobLogger.log("请求地址:"+url);
            for(int i=0;i<lstYlqxjyxk.size();i++){
                String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<!DOCTYPE body SYSTEM \"http://10.64.1.135/dtd/ylqxjyqy.dtd\">\n" +
                        "<body>\n";
                MedInstruYlqxjyxk medInstruYlqxjyxk = medicalInstrumentsService.getYlqxjyxkBySqId(lstYlqxjyxk.get(i));
                xmlStr = xmlStr + Xml4BeanUtils.parseBeanToXmlV2(medInstruYlqxjyxk) + "\n";
                xmlStr += "</body>";
                response = CallXmlUtils.sendCallRequest(url,xmlStr,MedicalInstrumentsConfiguration.getAdminConfig().getPassword(),
                        MedicalInstrumentsConfiguration.getAdminConfig().getUsername(),"医疗器械经营许可（备案）");
                XxlJobLogger.log("["+lstYlqxjyxk.get(i)+"]请求结果:"+response);
                processResponse(medicalInstrumentsService, response,
                        MedicalInstrumentsType.MEDICAL_INSTRUMENTS_TYPE_YLQXJYXK.getName(),lstYlqxjyxk.get(i));
            }
            XxlJobLogger.log("医疗器械经营许可(备案)任务执行了！！");
            return ReturnT.SUCCESS;
        }catch (Exception e){
            XxlJobLogger.log(e.getMessage());
            return ReturnT.FAIL;
        }
    }
}
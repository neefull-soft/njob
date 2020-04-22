package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.config.MedicalInstrumentsConfiguration;
import com.xxl.job.executor.service.jobhandler.config.MedicalInstrumentsType;
import com.xxl.job.executor.service.jobhandler.model.MedInstruYlqxscxk;
import com.xxl.job.executor.service.jobhandler.model.MedInstruYlqxscxkCpZb;
import com.xxl.job.executor.service.jobhandler.service.MedicalInstrumentsService;
import com.xxl.job.executor.service.jobhandler.utils.CallXmlUtils;
import com.xxl.job.executor.service.jobhandler.utils.Xml4BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 医疗器械生产许可(备案)数据
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-08-27 15:38
 **/
@Component
public class MedicalInstrumentsYlqxscxkStrategy extends MedicalInstrumentsStrategy{

    private static final Logger LOGGER = Logger.getLogger(MedicalInstrumentsYlqxscxkStrategy.class);

    @Autowired
    MedicalInstrumentsService medicalInstrumentsService;

    @Override
    public ReturnT<String> doAction() throws Exception {
        try {
            //先获取数据
            List<String> lstYlqxscxk = medicalInstrumentsService.getUnUploadYlqxSqId(MedicalInstrumentsType.MEDICAL_INSTRUMENTS_TYPE_YLQXSCXK.getName());
            if(lstYlqxscxk == null || lstYlqxscxk.size() == 0){
                XxlJobLogger.log("医疗器械生产许可(备案)新增数据为0");
                return ReturnT.SUCCESS;
            }else{
                XxlJobLogger.log("医疗器械生产许可(备案)新增数据为:"+lstYlqxscxk.size());
            }

            String response = "";  //请求返回的结果
            String url = MedicalInstrumentsConfiguration.getAdminConfig().getYlqxscxkUrl();
            XxlJobLogger.log("请求地址:"+url);
            for(int i=0;i<lstYlqxscxk.size();i++){
                String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<!DOCTYPE body SYSTEM \"http://10.64.1.135/dtd/ylqxscqy.dtd\">\n" +
                        "<body>\n";
                MedInstruYlqxscxk medInstruYlqxscxk = medicalInstrumentsService.getYlqxscxkBySqId(lstYlqxscxk.get(i));
                xmlStr = xmlStr + Xml4BeanUtils.parseBeanToXmlV2(medInstruYlqxscxk) + "\n";
                List<MedInstruYlqxscxkCpZb> lstCpZb = medicalInstrumentsService.getgetYlqxscxkCpZbBySqId(lstYlqxscxk.get(i));
                if(lstCpZb != null && lstCpZb.size() > 0){
                    for(int j=0;j<lstCpZb.size();j++){
                        xmlStr = xmlStr + Xml4BeanUtils.parseBeanToXmlV2(lstCpZb.get(j)) + "\n";
                    }
                }
                xmlStr += "</body>";
                response = CallXmlUtils.sendCallRequest(url,xmlStr,MedicalInstrumentsConfiguration.getAdminConfig().getPassword(),
                        MedicalInstrumentsConfiguration.getAdminConfig().getUsername(),"医疗器械生产许可（备案）");
                XxlJobLogger.log("["+lstYlqxscxk.get(i)+"]请求结果:"+response);
                processResponse(medicalInstrumentsService, response,
                        MedicalInstrumentsType.MEDICAL_INSTRUMENTS_TYPE_YLQXSCXK.getName(),lstYlqxscxk.get(i));
            }
            XxlJobLogger.log("医疗器械生产许可(备案)任务执行了！！");
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            XxlJobLogger.log(e.getMessage());
            return ReturnT.FAIL;
        }
    }
}
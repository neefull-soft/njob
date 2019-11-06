package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.config.MedicalInstrumentsConfiguration;
import com.xxl.job.executor.service.jobhandler.config.MedicalInstrumentsType;
import com.xxl.job.executor.service.jobhandler.model.MedInstruYlqxwtscba;
import com.xxl.job.executor.service.jobhandler.model.MedInstruYlqxwtscbaCpZb;
import com.xxl.job.executor.service.jobhandler.service.MedicalInstrumentsService;
import com.xxl.job.executor.service.jobhandler.utils.CallXmlUtils;
import com.xxl.job.executor.service.jobhandler.utils.Xml4BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 医疗器械委托生产备案
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-08-29 15:50
 **/
@Component
public class MedicalInstrumentsYlqxwtscbaStrategy extends MedicalInstrumentsStrategy{

    private static final Logger LOGGER = Logger.getLogger(MedicalInstrumentsYlqxwtscbaStrategy.class);

    @Autowired
    MedicalInstrumentsService medicalInstrumentsService;

    @Override
    public ReturnT<String> doAction() throws Exception {
        try {
            //先获取数据
            List<String> lstYlqxwtscba = medicalInstrumentsService.getUnUploadYlqxSqId(MedicalInstrumentsType.MEDICAL_INSTRUMENTS_TYPE_YLQXWTSCBA.getName());
            if(lstYlqxwtscba == null || lstYlqxwtscba.size() == 0){
                XxlJobLogger.log("医疗器械委托生产备案新增数据为0");
                return ReturnT.SUCCESS;
            }else{
                XxlJobLogger.log("医疗器械委托生产备案新增数据为:"+lstYlqxwtscba.size());
            }

            String response = "";  //请求返回的结果
            String url = MedicalInstrumentsConfiguration.getAdminConfig().getYlqxscxkUrl();
            XxlJobLogger.log("请求地址:"+url);
            for(int i=0;i<lstYlqxwtscba.size();i++){
                String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<!DOCTYPE body SYSTEM \"http://10.64.1.135/dtd/ylqxwtscba.dtd\">\n" +
                        "<body>\n";
                MedInstruYlqxwtscba medInstruYlqxwtscba = medicalInstrumentsService.getYlqxwtscbaBySqId(lstYlqxwtscba.get(i));
                xmlStr = xmlStr + Xml4BeanUtils.parseBeanToXmlV2(medInstruYlqxwtscba) + "\n";
                List<MedInstruYlqxwtscbaCpZb> lstCpZb = medicalInstrumentsService.getYlqxwtscbaCpZbBySqId(lstYlqxwtscba.get(i));
                if(lstCpZb != null && lstCpZb.size() > 0){
                    for(int j=0;j<lstCpZb.size();j++){
                        xmlStr = xmlStr + Xml4BeanUtils.parseBeanToXmlV2(lstCpZb.get(j)) + "\n";
                    }
                }
                xmlStr += "</body>";
                response = CallXmlUtils.sendCallRequest(url,xmlStr,MedicalInstrumentsConfiguration.getAdminConfig().getPassword(),
                        MedicalInstrumentsConfiguration.getAdminConfig().getUsername(),"医疗器械委托生产备案");
                XxlJobLogger.log("["+lstYlqxwtscba.get(i)+"]请求结果:"+response);
                processResponse(medicalInstrumentsService, response,
                        MedicalInstrumentsType.MEDICAL_INSTRUMENTS_TYPE_YLQXWTSCBA.getName(),lstYlqxwtscba.get(i));
            }
            XxlJobLogger.log("医疗器械委托生产备案任务执行了！！");
            return ReturnT.SUCCESS;
        }catch (Exception e){
            XxlJobLogger.log(e.getMessage());
            return ReturnT.FAIL;
        }
    }
}
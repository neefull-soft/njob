package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.config.MedicalInstrumentsConfiguration;
import com.xxl.job.executor.service.jobhandler.config.MedicalInstrumentsType;
import com.xxl.job.executor.service.jobhandler.model.MedInstruYlqxwlxsba;
import com.xxl.job.executor.service.jobhandler.model.MedInstruYlqxwlxsbaDsfZb;
import com.xxl.job.executor.service.jobhandler.service.MedicalInstrumentsService;
import com.xxl.job.executor.service.jobhandler.utils.CallXmlUtils;
import com.xxl.job.executor.service.jobhandler.utils.Xml4BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 医疗器械网络销售备案
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-08-29 17:01
 **/
@Component
public class MedicalInstrumentsYlqxwlxsbaStrategy extends MedicalInstrumentsStrategy{

    private static final Logger LOGGER = Logger.getLogger(MedicalInstrumentsYlqxwlxsbaStrategy.class);

    @Autowired
    MedicalInstrumentsService medicalInstrumentsService;

    @Override
    public ReturnT<String> doAction() throws Exception {
        try {
            //先获取数据
            List<String> lstYlqxwlxsba = medicalInstrumentsService.getUnUploadYlqxSqId(MedicalInstrumentsType.MEDICAL_INSTRUMENTS_TYPE_YLQXWLXSBA.getName());
            if(lstYlqxwlxsba == null || lstYlqxwlxsba.size() == 0){
                XxlJobLogger.log("医疗器械网络销售备案新增数据为0");
                return ReturnT.SUCCESS;
            }else{
                XxlJobLogger.log("医疗器械网络销售备案新增数据为:"+lstYlqxwlxsba.size());
            }

            String response = "";  //请求返回的结果
            String url = MedicalInstrumentsConfiguration.getAdminConfig().getYlqxscxkUrl();
            XxlJobLogger.log("请求地址:"+url);
            for(int i=0;i<lstYlqxwlxsba.size();i++){
                String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<!DOCTYPE body SYSTEM \"http://10.64.1.135/dtd/ylqxwlxsba.dtd\">\n" +
                        "<body>\n";
                MedInstruYlqxwlxsba medInstruYlqxwlxsba = medicalInstrumentsService.getYlqxwlxsbaBySqId(lstYlqxwlxsba.get(i));
                xmlStr = xmlStr + Xml4BeanUtils.parseBeanToXmlV2(medInstruYlqxwlxsba) + "\n";
                List<MedInstruYlqxwlxsbaDsfZb> lstCpZb = medicalInstrumentsService.getYlqxwlxsbaDsfZbBySqId(lstYlqxwlxsba.get(i));
                if(lstCpZb != null && lstCpZb.size() > 0){
                    for(int j=0;j<lstCpZb.size();j++){
                        xmlStr = xmlStr + Xml4BeanUtils.parseBeanToXmlV2(lstCpZb.get(j)) + "\n";
                    }
                }
                xmlStr += "</body>";
                response = CallXmlUtils.sendCallRequest(url,xmlStr,MedicalInstrumentsConfiguration.getAdminConfig().getPassword(),
                        MedicalInstrumentsConfiguration.getAdminConfig().getUsername(),"医疗器械网络销售备案");
                XxlJobLogger.log("["+lstYlqxwlxsba.get(i)+"]请求结果:"+response);
                processResponse(medicalInstrumentsService, response,
                        MedicalInstrumentsType.MEDICAL_INSTRUMENTS_TYPE_YLQXWLXSBA.getName(),lstYlqxwlxsba.get(i));
            }
            XxlJobLogger.log("医疗器械网络销售备案任务执行了！！");
            return ReturnT.SUCCESS;
        }catch (Exception e){
            XxlJobLogger.log(e.getMessage());
            return ReturnT.FAIL;
        }
    }
}
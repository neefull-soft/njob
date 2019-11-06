package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.config.MedicalInstrumentsConfiguration;
import com.xxl.job.executor.service.jobhandler.config.MedicalInstrumentsType;
import com.xxl.job.executor.service.jobhandler.model.MedInstruYlqxwljyfwdsf;
import com.xxl.job.executor.service.jobhandler.service.MedicalInstrumentsService;
import com.xxl.job.executor.service.jobhandler.utils.CallXmlUtils;
import com.xxl.job.executor.service.jobhandler.utils.Xml4BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 医疗器械网络交易服务第三方平台备案
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-08-29 16:33
 **/
@Component
public class MedicalInstrumentsYlqxwljyfwdsfStrategy extends MedicalInstrumentsStrategy{

    private static final Logger LOGGER = Logger.getLogger(MedicalInstrumentsYlqxwljyfwdsfStrategy.class);

    @Autowired
    MedicalInstrumentsService medicalInstrumentsService;

    @Override
    public ReturnT<String> doAction() throws Exception {
        try {
            //先获取数据
            List<String> lstYlqxwljyfwdsf = medicalInstrumentsService.getUnUploadYlqxSqId(MedicalInstrumentsType.MEDICAL_INSTRUMENTS_TYPE_YLQXWLJYFWDSF.getName());
            if(lstYlqxwljyfwdsf == null || lstYlqxwljyfwdsf.size() == 0){
                XxlJobLogger.log("医疗器械网络交易服务第三方平台备案新增数据为0");
                return ReturnT.SUCCESS;
            }else{
                XxlJobLogger.log("医疗器械网络交易服务第三方平台备案新增数据为:"+lstYlqxwljyfwdsf.size());
            }

            String response = "";  //请求返回的结果
            String url = MedicalInstrumentsConfiguration.getAdminConfig().getYlqxscxkUrl();
            XxlJobLogger.log("请求地址:"+url);
            for(int i=0;i<lstYlqxwljyfwdsf.size();i++){
                String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<!DOCTYPE body SYSTEM \"http://10.64.1.135/dtd/ylqxwljyfw.dtd\">\n" +
                        "<body>\n";
                MedInstruYlqxwljyfwdsf medInstruYlqxwljyfwdsf = medicalInstrumentsService.getYlqxwljyfwdsfBySqId(lstYlqxwljyfwdsf.get(i));
                xmlStr = xmlStr + Xml4BeanUtils.parseBeanToXmlV2(medInstruYlqxwljyfwdsf) + "\n";
                xmlStr += "</body>";
                response = CallXmlUtils.sendCallRequest(url,xmlStr,MedicalInstrumentsConfiguration.getAdminConfig().getPassword(),
                        MedicalInstrumentsConfiguration.getAdminConfig().getUsername(),"医疗器械网络交易服务第三方平台备案");
                XxlJobLogger.log("["+lstYlqxwljyfwdsf.get(i)+"]请求结果:"+response);
                processResponse(medicalInstrumentsService, response,
                        MedicalInstrumentsType.MEDICAL_INSTRUMENTS_TYPE_YLQXWLJYFWDSF.getName(),lstYlqxwljyfwdsf.get(i));
            }
            XxlJobLogger.log("医疗器械网络交易服务第三方平台备案任务执行了！！");
            return ReturnT.SUCCESS;
        }catch (Exception e){
            XxlJobLogger.log(e.getMessage());
            return ReturnT.FAIL;
        }
    }
}
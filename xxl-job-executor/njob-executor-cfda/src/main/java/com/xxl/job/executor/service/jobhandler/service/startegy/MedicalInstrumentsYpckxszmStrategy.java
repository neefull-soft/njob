package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.config.MedicalInstrumentsConfiguration;
import com.xxl.job.executor.service.jobhandler.config.MedicalInstrumentsType;
import com.xxl.job.executor.service.jobhandler.model.nationalShare.YPCKXSZMJKGJModel;
import com.xxl.job.executor.service.jobhandler.model.nationalShare.YPCKXSZMModel;
import com.xxl.job.executor.service.jobhandler.service.MedicalInstrumentsService;
import com.xxl.job.executor.service.jobhandler.utils.CallXmlUtils;
import com.xxl.job.executor.service.jobhandler.utils.Xml4BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicalInstrumentsYpckxszmStrategy extends MedicalInstrumentsStrategy {

    @Autowired
    MedicalInstrumentsService medicalInstrumentsService;

    @Override
    public ReturnT<String> doAction() throws Exception {
        try {
            //先获取数据
            List<YPCKXSZMModel> lstYpckxszm = medicalInstrumentsService.getUnUploadYpckxszm();
            if(lstYpckxszm == null || lstYpckxszm.size() == 0){
                XxlJobLogger.log("药品出口销售证明新增数据为0");
                return ReturnT.SUCCESS;
            }else{
                XxlJobLogger.log("药品出口销售证明新增数据为:"+lstYpckxszm.size());
            }

            String response = "";  //请求返回的结果
            String url = MedicalInstrumentsConfiguration.getAdminConfig().getYlqxscxkUrl();
            XxlJobLogger.log("请求地址:"+url);
            for(int i=0;i<lstYpckxszm.size();i++){
                String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<!DOCTYPE body SYSTEM \"http://10.64.1.135/dtd/YPCKXSZM.dtd\">\n" +
                        "<body>\n";
                xmlStr = xmlStr + Xml4BeanUtils.parseBeanToXmlV2(lstYpckxszm.get(i)) + "\n";
                List<YPCKXSZMJKGJModel> lstJkgj = medicalInstrumentsService.getYpckxszmJkgjBySqId(lstYpckxszm.get(i).getSQID());
                if(lstJkgj != null && lstJkgj.size() > 0){
                    for(int j=0;j<lstJkgj.size();j++){
                        xmlStr = xmlStr + Xml4BeanUtils.parseBeanToXmlV2(lstJkgj.get(j)) + "\n";
                    }
                }
                xmlStr += "</body>";
                response = CallXmlUtils.sendCallRequestForNMPA(url,xmlStr,MedicalInstrumentsConfiguration.getAdminConfig().getPassword(),
                        MedicalInstrumentsConfiguration.getAdminConfig().getUsername(),"药品出口销售证明主体信息");
                XxlJobLogger.log("["+lstYpckxszm.get(i).getSQID()+"]请求结果:"+response);
                processResponse(medicalInstrumentsService, response,
                        MedicalInstrumentsType.MEDICAL_INSTRUMENTS_TYPE_YPCKXSZM.getName(),lstYpckxszm.get(i).getSQID());
            }
            XxlJobLogger.log("药品出口销售证明执行了！！");
            return ReturnT.SUCCESS;
        }catch (Exception e){
            XxlJobLogger.log(e.getMessage());
            return ReturnT.FAIL;
        }
    }
}

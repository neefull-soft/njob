package com.xxl.job.executor.service.jobhandler.service.startegy.nationalShare;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.config.MedicalInstrumentsConfiguration;
import com.xxl.job.executor.service.jobhandler.config.NationalShareType;
import com.xxl.job.executor.service.jobhandler.model.nationalShare.NationalShareYpGSP;
import com.xxl.job.executor.service.jobhandler.service.NationalShareService;
import com.xxl.job.executor.service.jobhandler.utils.CallXmlUtils;
import com.xxl.job.executor.service.jobhandler.utils.Xml4BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NationalShareYpGSPStrategy extends NationalShareStrategy{

    private static final Logger LOGGER = Logger.getLogger(NationalShareYpGSPStrategy.class);

    @Autowired
    NationalShareService nationalShareService;

    @Override
    public ReturnT<String> doAction() throws Exception {
        try {
            //先获取数据
            List<NationalShareYpGSP> lstYpgsp = nationalShareService.getYpgspLst();
            if(lstYpgsp == null || lstYpgsp.size() == 0){
                XxlJobLogger.log("药品GSP认证新增数据为0");
                return ReturnT.SUCCESS;
            }else{
                XxlJobLogger.log("药品GSP认证新增数据为:"+lstYpgsp.size());
            }

            String response = "";  //请求返回的结果
            String url = MedicalInstrumentsConfiguration.getAdminConfig().getYlqxscxkUrl();
            XxlJobLogger.log("请求地址:"+url);
            for(int i=0;i<lstYpgsp.size();i++){
                String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<!DOCTYPE body SYSTEM \"http://10.64.1.135/dtd/ypgsprz.dtd\">\n" +
                        "<body>\n";
                xmlStr = xmlStr + Xml4BeanUtils.parseBeanToXmlV2(lstYpgsp.get(i)) + "\n";
                xmlStr += "</body>";
                response = CallXmlUtils.sendCallRequest(url,xmlStr,MedicalInstrumentsConfiguration.getAdminConfig().getPassword(),
                        MedicalInstrumentsConfiguration.getAdminConfig().getUsername(),"药品GSP认证");
                XxlJobLogger.log("["+lstYpgsp.get(i)+"]请求结果:"+response);
                processResponse(nationalShareService, response,
                        NationalShareType.NATIONAL_SHARE_TYPE_YPGSP.getName(),lstYpgsp.get(i).getID());
            }

            XxlJobLogger.log("药品GSP认证任务执行了！！");
            return ReturnT.SUCCESS;
        }catch (Exception e){
            XxlJobLogger.log(e.getMessage());
            return ReturnT.FAIL;
        }
    }
}

package com.xxl.job.executor.service.jobhandler.service.startegy.nationalShare;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.config.MedicalInstrumentsConfiguration;
import com.xxl.job.executor.service.jobhandler.config.NationalShareType;
import com.xxl.job.executor.service.jobhandler.model.nationalShare.NationalShareYlqxjy;
import com.xxl.job.executor.service.jobhandler.service.NationalShareService;
import com.xxl.job.executor.service.jobhandler.utils.CallXmlUtils;
import com.xxl.job.executor.service.jobhandler.utils.Xml4BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NationalShareYlqxjyStrategy extends NationalShareStrategy{

    private static final Logger LOGGER = Logger.getLogger(NationalShareYlqxjyStrategy.class);

    @Autowired
    NationalShareService nationalShareService;

    @Override
    public ReturnT<String> doAction() throws Exception {
        try {
            //先获取数据
            List<NationalShareYlqxjy> lstYlqxjy = nationalShareService.getYlqxjyLst();
            if(lstYlqxjy == null || lstYlqxjy.size() == 0){
                XxlJobLogger.log("医疗器械经营新增数据为0");
                return ReturnT.SUCCESS;
            }else{
                XxlJobLogger.log("医疗器械经营新增数据为:"+lstYlqxjy.size());
            }

            String response = "";  //请求返回的结果
            String url = MedicalInstrumentsConfiguration.getAdminConfig().getYlqxscxkUrl();
            XxlJobLogger.log("请求地址:"+url);
            for(int i=0;i<lstYlqxjy.size();i++){
                String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<!DOCTYPE body SYSTEM \"http://10.64.1.135/dtd/ylqxjyqy.dtd\">\n" +
                        "<body>\n";
                xmlStr = xmlStr + Xml4BeanUtils.parseBeanToXmlV2(lstYlqxjy.get(i)) + "\n";
                xmlStr += "</body>";
                response = CallXmlUtils.sendCallRequest(url,xmlStr,MedicalInstrumentsConfiguration.getAdminConfig().getPassword(),
                        MedicalInstrumentsConfiguration.getAdminConfig().getUsername(),"医疗器械经营企业");
                XxlJobLogger.log("["+lstYlqxjy.get(i)+"]请求结果:"+response);
                processResponse(nationalShareService, response,
                        NationalShareType.NATIONAL_SHARE_TYPE_YLQXJY.getName(),lstYlqxjy.get(i).getID());
            }

            XxlJobLogger.log("医疗器械经营执行了！！");
            return ReturnT.SUCCESS;
        }catch (Exception e){
            XxlJobLogger.log(e.getMessage());
            return ReturnT.FAIL;
        }
    }
}

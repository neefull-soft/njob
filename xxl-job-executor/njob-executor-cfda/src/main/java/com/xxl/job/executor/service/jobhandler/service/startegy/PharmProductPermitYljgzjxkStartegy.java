package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.config.PharmProductPermitConfiguration;
import com.xxl.job.executor.service.jobhandler.config.PharmProductPermitType;
import com.xxl.job.executor.service.jobhandler.model.PharmProductPmtYljgzjxk;
import com.xxl.job.executor.service.jobhandler.service.PharmaceuticalProductionPermitService;
import com.xxl.job.executor.service.jobhandler.utils.HttpUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 医疗机构制剂许可
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-04-01 15:26
 **/
@Component
public class PharmProductPermitYljgzjxkStartegy extends PharmaceuticalProductionPermitStrategy {

    private static final Logger LOGGER = Logger.getLogger(PharmProductPermitYljgzjxkStartegy.class);

    @Autowired
    PharmaceuticalProductionPermitService pharmaceuticalProductionPermitService;

    @Override
    public ReturnT<String> doAction() throws Exception {
        //先获取数据
        List<PharmProductPmtYljgzjxk> lstYljgzjxk = pharmaceuticalProductionPermitService.getYljgzjxkAllDate();
        if(lstYljgzjxk == null || lstYljgzjxk.size() == 0){
            XxlJobLogger.log("医疗机构制剂许可新增数据为0");
            return ReturnT.SUCCESS;
        }else{
            XxlJobLogger.log("医疗机构制剂许可新增数据为"+lstYljgzjxk.size());
        }

        String postUrl = PharmProductPermitConfiguration.getAdminConfig().getYljgzjxkUrl();  // 请求路径

        List<PharmProductPmtYljgzjxk> lstParam = new ArrayList<PharmProductPmtYljgzjxk>();  // 每次存放的列表
        JSONObject obj = new JSONObject();    //每次存放的json实体
        String response = "";   //请求返回的结果

        for(PharmProductPmtYljgzjxk entYljgzjxk : lstYljgzjxk){
            obj = new JSONObject();
            lstParam = new ArrayList<PharmProductPmtYljgzjxk>();
            lstParam.add(entYljgzjxk);
            obj.put("param",lstParam);
            response = HttpUtils.sendHeaderPost(postUrl, obj.get("param").toString(),
                    PharmProductPermitConfiguration.getAdminConfig().getSendAccount(),
                    PharmProductPermitConfiguration.getAdminConfig().getSendPwd());
//            response = new String(response.getBytes("iso8859-1"),"utf-8");
            System.out.println("["+entYljgzjxk.getId()+"]请求结果:"+response);
            XxlJobLogger.log("["+entYljgzjxk.getId()+"]请求结果:"+response);
            LOGGER.info("["+entYljgzjxk.getId()+"]请求结果:"+response);
            processResponse(pharmaceuticalProductionPermitService, response,
                    PharmProductPermitType.PHARM_PRODUCT_PERMIT_TYPE_YLJGZJXK.getName(),entYljgzjxk.getId());
        }
        System.out.println("医疗机构制剂许可任务执行了！！");
        return ReturnT.SUCCESS;
    }
}
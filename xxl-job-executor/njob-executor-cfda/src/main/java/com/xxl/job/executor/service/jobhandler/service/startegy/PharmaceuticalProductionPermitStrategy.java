package com.xxl.job.executor.service.jobhandler.service.startegy;

import com.alibaba.fastjson.JSONObject;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.executor.service.jobhandler.service.PharmaceuticalProductionPermitService;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-04-01 15:18
 **/
public abstract class PharmaceuticalProductionPermitStrategy {
    public abstract ReturnT<String> doAction() throws Exception;

    /**
     * 处理接口返回结果
     * @param pharmaceuticalProductionPermitService
     * @param response
     * @param tableName
     * @param sqId
     */
    public void processResponse(PharmaceuticalProductionPermitService pharmaceuticalProductionPermitService,
                                String response, String tableName, String sqId){
        String flag = "0";
        String errorcode = this.getValByKey(response, "code");
        String errormsg = this.getValByKey(response, "msg");
        String udate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        if("1".equals(errorcode)){
            flag = "1";
        }else{
            flag = "-1";
        }
        pharmaceuticalProductionPermitService.updateYljgzjAndYpscXkStatus(tableName, sqId, udate, flag, errormsg);
    }

    /**
     * 获取返回 json 字符串key 对应的内容
     */
    private String getValByKey(String json, String key) {
        try {
            JSONObject jsonStr = (JSONObject) JSONObject.parse(json);
            return jsonStr.getString(key);
        } catch (Exception e) {
            // 返回的不是 json
            return "-1";
        }
    }
}
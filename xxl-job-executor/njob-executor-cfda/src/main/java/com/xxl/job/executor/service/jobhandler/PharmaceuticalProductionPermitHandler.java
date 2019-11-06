package com.xxl.job.executor.service.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.service.startegy.PharmProductPermitYljgzjxkStartegy;
import com.xxl.job.executor.service.jobhandler.service.startegy.PharmProductPermitYpscxkChangeStartegy;
import com.xxl.job.executor.service.jobhandler.service.startegy.PharmProductPermitYpscxkStartegy;
import com.xxl.job.executor.service.jobhandler.service.startegy.PharmaceuticalProductionPermitContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 药品生产许可、医疗机构制剂许可
 * 国家总局数据对接
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-04-01 14:21
 **/
@Component
@JobHandler("PharmaceuticalProductionPermitHandler")
public class PharmaceuticalProductionPermitHandler extends IJobHandler {

    @Autowired
    PharmProductPermitYljgzjxkStartegy pharmProductPermitYljgzjxkStartegy;
    @Autowired
    PharmProductPermitYpscxkStartegy pharmProductPermitYpscxkStartegy;
    @Autowired
    PharmProductPermitYpscxkChangeStartegy pharmProductPermitYpscxkChangeStartegy;

    /**
     * @param param 传进来参数，代表的是数据类型
     * @return 成功或者失败
     * @throws Exception
     */
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        try {
            if (null != param && !"".equals(param)) {
                XxlJobLogger.log("开始处理" + param + "数据");
                if ("医疗机构制剂许可".equals(param)) {
                    new PharmaceuticalProductionPermitContent(pharmProductPermitYljgzjxkStartegy).doAction();
                } else if ("药品生产许可新办换发".equals(param)) {
                    new PharmaceuticalProductionPermitContent(pharmProductPermitYpscxkStartegy).doAction();
                } else if ("药品生产许可变更申报".equals(param)) {
                    new PharmaceuticalProductionPermitContent(pharmProductPermitYpscxkChangeStartegy).doAction();
                }
            }
        } catch (Exception e) {
            XxlJobLogger.log(e.getMessage());
            return ReturnT.FAIL;
        }
        return ReturnT.SUCCESS;
    }
}
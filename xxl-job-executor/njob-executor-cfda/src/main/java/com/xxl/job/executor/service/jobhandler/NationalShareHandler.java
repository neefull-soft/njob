package com.xxl.job.executor.service.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.service.startegy.nationalShare.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@JobHandler("NationalShareHandler")
public class NationalShareHandler extends IJobHandler {

    @Autowired
    NationalShareYpjyxkStrategy nationalShareYpjyxkStrategy;
    @Autowired
    NationalShareYpscxkStrategy nationalShareYpscxkStrategy;
    @Autowired
    NationalShareYpGMPStrategy nationalShareYpGMPStrategy;
    @Autowired
    NationalShareYpGSPStrategy nationalShareYpGSPStrategy;
    @Autowired
    NationalShareYlqxscxkStrategy nationalShareYlqxscxkStrategy;
    @Autowired
    NationalShareYlqxjyStrategy nationalShareYlqxjyStrategy;
    @Autowired
    NationalShareYlqxcpbazcStrategy nationalShareYlqxcpbazcStrategy;

    /**
     * @param param 传进来参数，代表的是数据类型
     * @return 成功或者失败
     * @throws Exception
     */
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        try{
            if (null != param && !"".equals(param)) {
                XxlJobLogger.log("开始处理" + param + "数据");
                if ("药品经营许可".equals(param)) {
                    new NationalShareContent(nationalShareYpjyxkStrategy).doAction();
                }else if ("药品生产许可".equals(param)) {
                    new NationalShareContent(nationalShareYpscxkStrategy).doAction();
                }else if ("药品GMP认证".equals(param)) {
                    new NationalShareContent(nationalShareYpGMPStrategy).doAction();
                }else if ("药品GSP认证".equals(param)) {
                    new NationalShareContent(nationalShareYpGSPStrategy).doAction();
                }else if ("医疗器械生产许可（备案）".equals(param)) {
                    new NationalShareContent(nationalShareYlqxscxkStrategy).doAction();
                }else if ("医疗器械经营".equals(param)) {
                    new NationalShareContent(nationalShareYlqxjyStrategy).doAction();
                }else if ("国产器械产品（一、二类）".equals(param)) {
                    new NationalShareContent(nationalShareYlqxcpbazcStrategy).doAction();
                }
            }
        } catch (Exception e) {
            XxlJobLogger.log(e.getMessage());
            return ReturnT.FAIL;
        }
        return ReturnT.SUCCESS;
    }
}

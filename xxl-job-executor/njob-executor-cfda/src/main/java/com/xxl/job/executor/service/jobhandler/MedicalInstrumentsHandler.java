package com.xxl.job.executor.service.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.service.jobhandler.service.startegy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 医疗器械数据对接
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-08-27 15:24
 **/
@Component
@JobHandler("MedicalInstrumentsHandler")
public class MedicalInstrumentsHandler extends IJobHandler {

    @Autowired
    MedicalInstrumentsYlqxscxkStrategy medicalInstrumentsYlqxscxkStrategy;
    @Autowired
    MedicalInstrumentsYlqxjyxkStrategy medicalInstrumentsYlqxjyxkStrategy;
    @Autowired
    MedicalInstrumentsYlqxwtscbaStrategy medicalInstrumentsYlqxwtscbaStrategy;
    @Autowired
    MedicalInstrumentsYlqxwljyfwdsfStrategy medicalInstrumentsYlqxwljyfwdsfStrategy;
    @Autowired
    MedicalInstrumentsYlqxwlxsbaStrategy medicalInstrumentsYlqxwlxsbaStrategy;
    @Autowired
    MedicalInstrumentsYlqxscqyjcStrategy medicalInstrumentsYlqxscqyjcStrategy;

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
                if ("医疗器械生产许可".equals(param)) {
                    new MedicalInstrumentsContent(medicalInstrumentsYlqxscxkStrategy).doAction();
                }else if("医疗器械经营许可".equals(param)){
                    new MedicalInstrumentsContent(medicalInstrumentsYlqxjyxkStrategy).doAction();
                }else if("医疗器械委托生产备案".equals(param)){
                    new MedicalInstrumentsContent(medicalInstrumentsYlqxwtscbaStrategy).doAction();
                }else if("医疗器械网络交易服务第三方平台备案".equals(param)){
                    new MedicalInstrumentsContent(medicalInstrumentsYlqxwljyfwdsfStrategy).doAction();
                }else if("医疗器械网络销售备案".equals(param)){
                    new MedicalInstrumentsContent(medicalInstrumentsYlqxwlxsbaStrategy).doAction();
                }else if("医疗器械生产企业检查".equals(param)){
                    new MedicalInstrumentsContent(medicalInstrumentsYlqxscqyjcStrategy).doAction();
                }
            }
        } catch (Exception e) {
            XxlJobLogger.log(e.getMessage());
            return ReturnT.FAIL;
        }
        return ReturnT.SUCCESS;
    }
}
package com.xxl.job.executor.service.jobhandler.service;

import com.xxl.job.executor.service.jobhandler.model.*;
import com.xxl.job.executor.service.jobhandler.model.nationalShare.YPCKXSZMJKGJModel;
import com.xxl.job.executor.service.jobhandler.model.nationalShare.YPCKXSZMModel;

import java.util.List;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-08-27 15:51
 **/
public interface MedicalInstrumentsService {

    /**
     * 公共-提交完毕后更新提交状态
     * @param tableName
     * @param sqid
     * @param udate
     * @param flag
     * @param errinfo
     */
    public void updateYlqxUploadState(String tableName, String sqid, String udate, String flag, String errinfo);

    /**
     * 医疗器械生产企业检查数据提交后更新状态
     * @param sqid
     * @param udate
     * @param flag
     * @param errinfo
     */
    public void updateYlqxJCUploadState(String sqid, String udate, String flag, String errinfo);

    /**
     * 公共-获取未提交的数据sqid
     * @return
     */
    public List<String> getUnUploadYlqxSqId(String tableName);

    //****************************医疗器械生产许可********************************************

    /**
     * 根据sqid获取单条医疗器械生产许可数据
     * @param sqId
     * @return
     */
    public MedInstruYlqxscxk getYlqxscxkBySqId(String sqId);

    /**
     * 根据sqid获取单条医疗器械生产许可子表-列表产品数据
     * @param sqId
     * @return
     */
    public List<MedInstruYlqxscxkCpZb> getgetYlqxscxkCpZbBySqId(String sqId);

    //*******************************医疗器械经营许可*****************************************

    /**
     * 根据sqid获取单条医疗器械经营许可数据
     * @param sqId
     * @return
     */
    public MedInstruYlqxjyxk getYlqxjyxkBySqId(String sqId);

    //*******************************医疗器械委托生产备案*****************************************

    /**
     * 根据sqid获取单条医疗器械委托生产备案数据
     * @param sqId
     * @return
     */
    public MedInstruYlqxwtscba getYlqxwtscbaBySqId(String sqId);

    /**
     * 根据sqid获取单条医疗器械委托生产备案子表-列表产品数据
     * @param sqId
     * @return
     */
    public List<MedInstruYlqxwtscbaCpZb> getYlqxwtscbaCpZbBySqId(String sqId);

    //*******************************医疗器械网络交易服务第三方平台备案*****************************************

    /**
     * 根据sqid获取单条医疗器械网络交易服务第三方平台备案数据
     * @param sqId
     * @return
     */
    public MedInstruYlqxwljyfwdsf getYlqxwljyfwdsfBySqId(String sqId);

    //*******************************医疗器械网络销售备案*****************************************

    /**
     * 根据sqid获取单条医疗器械网络销售备案数据
     * @param sqId
     * @return
     */
    public MedInstruYlqxwlxsba getYlqxwlxsbaBySqId(String sqId);

    /**
     * 根据sqid获取单条医疗器械网络销售备案子表- 入驻类第三方平台信息
     * @param sqId
     * @return
     */
    public List<MedInstruYlqxwlxsbaDsfZb> getYlqxwlxsbaDsfZbBySqId(String sqId);

    //*******************************医疗器械生产企业检查*****************************************

    /**
     * 获取未提交的生产企业检查数据
     * @return
     */
    public List<MedInstruYlqxjcRwsjbxx> getYlqxjcRwsjbxxLst();

    /**
     * 根据任务书编号查询检查产品信息
     * @param rwsbh
     * @return
     */
    public List<MedInstruYlqxjcJccpxx> getYlqxjcJccpxxByRwsbh(String rwsbh);

    /**
     * 根据任务书编号查询任务检查项明细
     * @param rwsbh
     * @return
     */
    public List<MedInstruYlqxjcJcx> getYlqxjcJcxByRwsbh(String rwsbh);

    /**
     * 根据任务书编号查询任务检查组信息
     * @param rwsbh
     * @return
     */
    public List<MedInstruYlqxjcJcz> getYlqxjcJczByRwsbh(String rwsbh);

    //*******************************药品出口销售证明*****************************************

    /**
     * 获取未提交的药品出口销售证明数据
     * @return
     */
    public List<YPCKXSZMModel> getUnUploadYpckxszm();

    /**
     * 根据sqId获取关联的进口国家数据
     * @param sqId
     * @return
     */
    public List<YPCKXSZMJKGJModel> getYpckxszmJkgjBySqId(String sqId);
}
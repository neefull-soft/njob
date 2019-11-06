package com.xxl.job.executor.service.jobhandler.service.impl;

import com.xxl.job.executor.service.jobhandler.dao.MedicalInstrumentsDao;
import com.xxl.job.executor.service.jobhandler.model.*;
import com.xxl.job.executor.service.jobhandler.service.MedicalInstrumentsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-08-27 15:51
 **/
@Service
public class MedicalInstrumentsServiceImpl implements MedicalInstrumentsService {

    @Resource
    MedicalInstrumentsDao medicalInstrumentsDao;

    /**
     * 公共-提交完毕后更新提交状态
     * @param tableName
     * @param sqid
     * @param udate
     * @param flag
     * @param errinfo
     */
    @Override
    public void updateYlqxUploadState(String tableName, String sqid, String udate, String flag, String errinfo) {
        medicalInstrumentsDao.updateYlqxUploadState(tableName, sqid, udate, flag, errinfo);
    }

    /**
     * 医疗器械生产企业检查数据提交后更新状态
     * @param sqid
     * @param udate
     * @param flag
     * @param errinfo
     */
    @Override
    public void updateYlqxJCUploadState(String sqid, String udate, String flag, String errinfo) {
        medicalInstrumentsDao.updateYlqxJCUploadState(sqid, udate, flag, errinfo);
    }

    /**
     * 公共-获取未提交的数据sqid
     * @return
     */
    @Override
    public List<String> getUnUploadYlqxSqId(String tableName) {
        return medicalInstrumentsDao.getUnUploadYlqxSqId(tableName);
    }

    /**
     * 根据sqid获取单条医疗器械生产许可数据
     * @param sqId
     * @return
     */
    @Override
    public MedInstruYlqxscxk getYlqxscxkBySqId(String sqId) {
        return medicalInstrumentsDao.getYlqxscxkBySqId(sqId);
    }

    /**
     * 根据sqid获取单条医疗器械生产许可子表-列表产品数据
     * @param sqId
     * @return
     */
    @Override
    public List<MedInstruYlqxscxkCpZb> getgetYlqxscxkCpZbBySqId(String sqId) {
        return medicalInstrumentsDao.getgetYlqxscxkCpZbBySqId(sqId);
    }

    /**
     * 根据sqid获取单条医疗器械经营许可数据
     * @param sqId
     * @return
     */
    @Override
    public MedInstruYlqxjyxk getYlqxjyxkBySqId(String sqId) {
        return medicalInstrumentsDao.getYlqxjyxkBySqId(sqId);
    }

    /**
     * 根据sqid获取单条医疗器械委托生产备案数据
     * @param sqId
     * @return
     */
    @Override
    public MedInstruYlqxwtscba getYlqxwtscbaBySqId(String sqId) {
        return medicalInstrumentsDao.getYlqxwtscbaBySqId(sqId);
    }

    /**
     * 根据sqid获取单条医疗器械委托生产备案子表-列表产品数据
     * @param sqId
     * @return
     */
    @Override
    public List<MedInstruYlqxwtscbaCpZb> getYlqxwtscbaCpZbBySqId(String sqId) {
        return medicalInstrumentsDao.getYlqxwtscbaCpZbBySqId(sqId);
    }

    /**
     * 根据sqid获取单条医疗器械网络交易服务第三方平台备案数据
     * @param sqId
     * @return
     */
    @Override
    public MedInstruYlqxwljyfwdsf getYlqxwljyfwdsfBySqId(String sqId) {
        return medicalInstrumentsDao.getYlqxwljyfwdsfBySqId(sqId);
    }

    /**
     * 根据sqid获取单条医疗器械网络销售备案数据
     * @param sqId
     * @return
     */
    @Override
    public MedInstruYlqxwlxsba getYlqxwlxsbaBySqId(String sqId) {
        return medicalInstrumentsDao.getYlqxwlxsbaBySqId(sqId);
    }

    /**
     * 根据sqid获取单条医疗器械网络销售备案子表- 入驻类第三方平台信息
     * @param sqId
     * @return
     */
    @Override
    public List<MedInstruYlqxwlxsbaDsfZb> getYlqxwlxsbaDsfZbBySqId(String sqId) {
        return medicalInstrumentsDao.getYlqxwlxsbaDsfZbBySqId(sqId);
    }

    /**
     * 获取未提交的生产企业检查数据
     * @return
     */
    @Override
    public List<MedInstruYlqxjcRwsjbxx> getYlqxjcRwsjbxxLst() {
        return medicalInstrumentsDao.getYlqxjcRwsjbxxLst();
    }

    /**
     * 根据任务书编号查询检查产品信息
     * @param rwsbh
     * @return
     */
    @Override
    public List<MedInstruYlqxjcJccpxx> getYlqxjcJccpxxByRwsbh(String rwsbh) {
        return medicalInstrumentsDao.getYlqxjcJccpxxByRwsbh(rwsbh);
    }

    /**
     * 根据任务书编号查询任务检查项明细
     * @param rwsbh
     * @return
     */
    @Override
    public List<MedInstruYlqxjcJcx> getYlqxjcJcxByRwsbh(String rwsbh) {
        return medicalInstrumentsDao.getYlqxjcJcxByRwsbh(rwsbh);
    }

    /**
     * 根据任务书编号查询任务检查组信息
     * @param rwsbh
     * @return
     */
    @Override
    public List<MedInstruYlqxjcJcz> getYlqxjcJczByRwsbh(String rwsbh) {
        return medicalInstrumentsDao.getYlqxjcJczByRwsbh(rwsbh);
    }
}
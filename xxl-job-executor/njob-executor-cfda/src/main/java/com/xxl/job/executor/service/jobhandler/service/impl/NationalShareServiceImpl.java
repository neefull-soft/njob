package com.xxl.job.executor.service.jobhandler.service.impl;

import com.xxl.job.executor.service.jobhandler.dao.NationalShareDao;
import com.xxl.job.executor.service.jobhandler.model.nationalShare.*;
import com.xxl.job.executor.service.jobhandler.service.NationalShareService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NationalShareServiceImpl implements NationalShareService {

    @Resource
    private NationalShareDao nationalShareDao;

    /**
     * 公共-提交完毕后更新提交状态
     * @param tableName
     * @param sqid
     * @param udate
     * @param flag
     * @param errinfo
     */
    @Override
    public void updateUploadState(String tableName, String sqid, String udate, String flag, String errinfo) {
        nationalShareDao.updateUploadState(tableName,sqid,udate,flag,errinfo);
    }

    /**
     * 获取药品经营许可
     * @return
     */
    @Override
    public List<NationalShareYpjy> getYpjyLst() {
        return nationalShareDao.getYpjyLst();
    }

    /**
     * 获取药品生产许可
     * @return
     */
    @Override
    public List<NationalShareYpsc> getYpscLst() {
        return nationalShareDao.getYpscLst();
    }

    /**
     * 获取药品GMP认证
     * @return
     */
    @Override
    public List<NationalShareYpGMP> getYpgmpLst() {
        return nationalShareDao.getYpgmpLst();
    }

    /**
     * 获取药品GSP认证
     * @return
     */
    @Override
    public List<NationalShareYpGSP> getYpgspLst() {
        return nationalShareDao.getYpgspLst();
    }

    /**
     * 获取医疗器械生产许可（备案）
     * @return
     */
    @Override
    public List<NationalShareYlqxsc> getYlqxscLst() {
        return nationalShareDao.getYlqxscLst();
    }

    /**
     * 获取医疗器械经营
     * @return
     */
    @Override
    public List<NationalShareYlqxjy> getYlqxjyLst() {
        return nationalShareDao.getYlqxjyLst();
    }

    /**
     * 获取国产器械产品（一、二类）
     * @return
     */
    @Override
    public List<NationalShareYlqxcpbazc> getYlqxcpbazcLst() {
        return nationalShareDao.getYlqxcpbazcLst();
    }

}

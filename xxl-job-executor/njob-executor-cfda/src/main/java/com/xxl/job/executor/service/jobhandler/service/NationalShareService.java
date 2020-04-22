package com.xxl.job.executor.service.jobhandler.service;

import com.xxl.job.executor.service.jobhandler.model.nationalShare.*;

import java.util.List;

public interface NationalShareService {

    /**
     * 公共-提交完毕后更新提交状态
     * @param tableName
     * @param sqid
     * @param udate
     * @param flag
     * @param errinfo
     */
    public void updateUploadState(String tableName, String sqid, String udate, String flag, String errinfo);

    /**
     * 获取药品经营许可
     * @return
     */
    public List<NationalShareYpjy> getYpjyLst();

    /**
     * 获取药品生产许可
     * @return
     */
    public List<NationalShareYpsc> getYpscLst();


    /**
     * 获取药品GMP认证
     * @return
     */
    public List<NationalShareYpGMP> getYpgmpLst();

    /**
     * 获取药品GSP认证
     * @return
     */
    public List<NationalShareYpGSP> getYpgspLst();

    /**
     * 获取医疗器械生产许可（备案）
     * @return
     */
    public List<NationalShareYlqxsc> getYlqxscLst();

    /**
     * 获取医疗器械经营
     * @return
     */
    public List<NationalShareYlqxjy> getYlqxjyLst();

    /**
     * 获取国产器械产品（一、二类）
     * @return
     */
    public List<NationalShareYlqxcpbazc> getYlqxcpbazcLst();

}

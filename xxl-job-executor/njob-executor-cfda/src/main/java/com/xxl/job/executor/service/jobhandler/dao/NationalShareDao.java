package com.xxl.job.executor.service.jobhandler.dao;

import com.xxl.job.executor.service.jobhandler.model.nationalShare.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NationalShareDao {

    /**
     * 公共-提交完毕后更新提交状态
     * @param tableName
     * @param sqid
     * @param udate
     * @param flag
     * @param errinfo
     */
    public void updateUploadState(@Param("tableName") String tableName, @Param("sqid") String sqid, @Param("udate") String udate, @Param("flag") String flag, @Param("errinfo") String errinfo);

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

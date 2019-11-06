package com.xxl.job.executor.service.jobhandler.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 医疗器械处理Dao
 */
public interface MedicalDeviceDao {

    /**
     * 一类生产备案获取许可证数据和产品
     * @param tableName
     * @return
     */
    public List getMedicalDeviceDatYLSCBA(@Param("tableName") String tableName);

    public List getEquipmentProductsYLSCBA(@Param("sqid") String sqid);
    /**
     * 二、三类生产许可获取许可证数据和产品
     * @param tableName
     * @return
     */
    public List getMedicalDeviceDatSCXK(@Param("tableName") String tableName);

    public List getEquipmentProductsSCXK(@Param("sqid") String sqid);
    /**
     * 委托生产备案获取许可证数据和产品
     * @param tableName
     * @return
     */
    public List getMedicalDeviceDatWTSCBA(@Param("tableName") String tableName);

    public List getEquipmentProductsWTSCBA(@Param("sqid") String sqid);

    /**
     * 获取数据，根据表名称
     *
     * @param tableName
     * @return
     */
    public List getMedicalDeviceDataRLJYBA(@Param("tableName") String tableName);

    /**
     * 获取数据，根据表名称
     *
     * @param tableName
     * @return
     */
    public List getMedicalDeviceDataJYXK(@Param("tableName") String tableName);

    /**
     * 更新数据上传状态信息
     *
     * @param tableName 表名称
     * @param sqid      主键
     * @param udate     上传时间
     * @param flag      状态
     * @param errinfo   错误信息
     */
    public void updateMedicalDeviceData(@Param("tableName") String tableName, @Param("sqid") String sqid, @Param("udate") String udate, @Param("flag") String flag, @Param("errinfo") String errinfo);
}

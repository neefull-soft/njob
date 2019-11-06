package com.xxl.job.executor.service.jobhandler.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MedicalDevicesService {
    /**
     * 查询委托生产备案数据
     *
     * @param tableName
     * @return
     */
    public List getMedicalDeviceDatWTSCBA(String tableName);

    public List getEquipmentProductsWTSCBA(String sqid);
    /**
     * 查询一类生产备案数据
     *
     * @param tableName
     * @return
     */
    public List getMedicalDeviceDatYLSCBA(String tableName);

    /**
     * 生产备案产品信息
     *
     * @param sqid
     * @return
     */
    public List getEquipmentProductsYLSCBA(String sqid);

    /**
     * 二、三类生产许可获取许可证数据和产品
     * @param tableName
     * @return
     */
    public List getMedicalDeviceDatSCXK(String tableName);

    public List getEquipmentProductsSCXK(String sqid);

    /**
     * 二类经营备案
     *
     * @param tableName
     * @return
     */
    public List getMedicalDeviceDataRLJYBA(String tableName);

    /**
     * 器械经营许可信息
     *
     * @param tableName
     * @return
     */
    public List getMedicalDeviceDataJYXK(String tableName);

    /**
     * 通用更新数据状态
     *
     * @param tableName
     * @param sqid
     * @param udate
     * @param flag
     * @param errinfo
     */
    public void updateMedicalDeviceData(String tableName, String sqid, String udate, String flag, String errinfo);
}

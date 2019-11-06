package com.xxl.job.executor.service.jobhandler.service.impl;

import com.xxl.job.executor.service.jobhandler.dao.MedicalDeviceDao;
import com.xxl.job.executor.service.jobhandler.service.MedicalDevicesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MedicalDevicesServiceImpl implements MedicalDevicesService {
    @Resource
    MedicalDeviceDao medicalDeviceDao;

    @Override
    public List getMedicalDeviceDatWTSCBA(String tableName) {
        return medicalDeviceDao.getMedicalDeviceDatWTSCBA(tableName);
    }

    @Override
    public List getEquipmentProductsWTSCBA(String sqid) {
        return medicalDeviceDao.getEquipmentProductsWTSCBA(sqid);
    }

    @Override
    public List getMedicalDeviceDatYLSCBA(String tableName) {
        return medicalDeviceDao.getMedicalDeviceDatYLSCBA(tableName);
    }

    @Override
    public List getEquipmentProductsYLSCBA(String sqid) {
        return medicalDeviceDao.getEquipmentProductsYLSCBA(sqid);
    }

    @Override
    public List getMedicalDeviceDatSCXK(String tableName) {
        return medicalDeviceDao.getMedicalDeviceDatSCXK(tableName);
    }

    @Override
    public List getEquipmentProductsSCXK(String sqid) {
        return medicalDeviceDao.getEquipmentProductsSCXK(sqid);
    }

    @Override
    public List getMedicalDeviceDataRLJYBA(String tableName) {
        return medicalDeviceDao.getMedicalDeviceDataRLJYBA(tableName);
    }

    @Override
    public List getMedicalDeviceDataJYXK(String tableName) {
        return medicalDeviceDao.getMedicalDeviceDataJYXK(tableName);
    }

    @Override
    public void updateMedicalDeviceData(String tableName, String sqid, String udate, String flag, String errinfo) {
        medicalDeviceDao.updateMedicalDeviceData(tableName, sqid, udate, flag, errinfo);
    }
}

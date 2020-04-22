package com.xxl.job.executor.service.jobhandler.service.impl;

import com.xxl.job.executor.service.jobhandler.dao.CfdaDao;
import com.xxl.job.executor.service.jobhandler.service.CfdaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CfdaServiceImpl implements CfdaService {
    @Resource
    CfdaDao cfdaDao;

    @Override
    public String getMaxDate(String tableName, String dataFieldName, String pattern) {
        return cfdaDao.getMaxDate(tableName, dataFieldName, pattern);
    }

    @Override
    public Integer getMaxPrimaryKeyNumber(String tableName, String primaryKeyName) {
        return cfdaDao.getMaxPrimaryKeyNumber(tableName,primaryKeyName);
    }

    @Override
    public int save(String tableName, Map<String, String> params) {
        if(tableName.equals("t_share_ylqxscqy")){
            return cfdaDao.saveYlqxscqy(params);
        }else if(tableName.equals("t_share_ylqxjyqy")){
            return cfdaDao.saveYlqxjyqy(params);
        }else if(tableName.equals("t_share_gcylqx")){
            return cfdaDao.saveGcylqx(params);
        }else{
            return cfdaDao.save(tableName, params);
        }
    }

    @Override
    public int batchSave(String tableName, List<Map<String, String>> params) {
        return cfdaDao.batchSave(tableName, params);
    }
}

package com.xxl.job.executor.service.jobhandler.service;

import java.util.List;
import java.util.Map;

public interface CfdaService {

    /**
     * 获取数据库最大日期
     * tableName:数据库表名 ，dataFieldName 日期字段名称
     */
    public String getMaxDate(String tableName, String dataFieldName, String pattern);

    /**
     * 获取数据库最大主键值
     * @param tableName
     * @param primaryKeyName
     * @return
     */
    public Integer getMaxPrimaryKeyNumber(String tableName, String primaryKeyName);

    /**
     * 保存数据
     * 根据不同类型，自行实现处理方法
     *
     * @return
     */
    public int save(String tableName, Map<String, String> params);

    /**
     * 批量插入数据
     *
     * @param tableName 表名称
     * @param params    数据集合
     * @return
     */
    public int batchSave(String tableName, List<Map<String, String>> params);
}

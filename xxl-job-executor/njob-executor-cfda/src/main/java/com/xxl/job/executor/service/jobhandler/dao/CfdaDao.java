package com.xxl.job.executor.service.jobhandler.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CfdaDao {

    /**
     * 获取数据库最大日期
     * tableName:数据库表名 ，dataFieldName 日期字段名称
     */
    public String getMaxDate(@Param("tableName") String tableName, @Param("dataFieldName") String dataFieldName, @Param("pattern") String pattern);

    /**
     * 保存数据 单条保存
     * 根据不同类型，自行实现处理方法
     *
     * @return
     */
    public int save(@Param("tableName") String tableName, @Param("params") Map<String, String> params);

    /**
     * 批量插入数据
     *
     * @param tableName 表名称
     * @param params    数据集合
     * @return
     */
    public int batchSave(@Param("tableName") String tableName, @Param("paramsList") List<Map<String, String>> params);

    /**
     *
     */
    public List getYlqxscba(String tableName);
}

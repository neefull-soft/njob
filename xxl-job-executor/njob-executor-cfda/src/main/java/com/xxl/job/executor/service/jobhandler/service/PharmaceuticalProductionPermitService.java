package com.xxl.job.executor.service.jobhandler.service;

import com.xxl.job.executor.service.jobhandler.model.*;

import java.util.List;
import java.util.Map;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-04-01 15:01
 **/
public interface PharmaceuticalProductionPermitService {

    /**
     * 获取医疗机构制剂许可表【T_EXC_GJ_YLJGZJXKZXT】全部数据
     * @return
     */
    public List<PharmProductPmtYljgzjxk> getYljgzjxkAllDate();

    /**
     * 更新数据上传状态信息
     * @param tableName 表名称
     * @param sqid       主键
     * @param udate      上传时间
     * @param flag       状态
     * @param errinfo    错误信息
     */
    public void updateYljgzjAndYpscXkStatus(String tableName, String sqid, String udate, String flag, String errinfo);

    /**
     * 获取药品生产许可表全部数据
     * @return
     */
    public List<PharmPduPmtYpscxkQyxk> getYpscxkAllDate();

    /**
     * 根据sqid获取单个药品生产许可数据
     * @param sqid
     * @return
     */
    public PharmPduPmtYpscxkQyxk getYpscxkAllDateOne(String sqid);

    /**
     * 获取药品生产许可变更表全部数据
     * @return
     */
    public List<PharmPduPmtYpscxkBg> getYpscxkBgAllDate();

    /**
     * 获取药品生产企业许可证表
     * @param sqid
     * @return
     */
    public PharmPduPmtYpscxkQyxk getYpscQyxkz(String sqid);

    /**
     * 获取具备生产条件的生产范围
     * @param sqid
     * @return
     */
    public List<PharmPduPmtYpscxkscfw> getYpscJbsctjdscfw(String sqid);

    /**
     * 获取通过境外药品GMP认证
     * @param sqid
     * @return
     */
    public List<PharmPduPmtYpscxkGmp> getYpscTgjwypgmprz(String sqid);
}
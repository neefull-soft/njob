package com.xxl.job.executor.service.jobhandler.service.impl;

import com.xxl.job.executor.service.jobhandler.dao.PharmaceuticalProductionPermitDao;
import com.xxl.job.executor.service.jobhandler.model.*;
import com.xxl.job.executor.service.jobhandler.service.PharmaceuticalProductionPermitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-04-01 15:01
 **/
@Service
public class PharmaceuticalProductionPermitServiceImpl implements PharmaceuticalProductionPermitService {

    @Resource
    PharmaceuticalProductionPermitDao pharmaceuticalProductionPermitDao;

    /**
     * //获取医疗机构制剂许可表【T_EXC_GJ_YLJGZJXKZXT】全部数据
     * @return
     */
    @Override
    public List<PharmProductPmtYljgzjxk> getYljgzjxkAllDate() {
        return pharmaceuticalProductionPermitDao.getYljgzjxkAllDate();
    }

    /**
     * 更新数据上传状态信息
     * @param tableName 表名称
     * @param sqid       主键
     * @param udate      上传时间
     * @param flag       状态
     * @param errinfo    错误信息
     */
    @Override
    public void updateYljgzjAndYpscXkStatus(String tableName, String sqid, String udate, String flag, String errinfo) {
        pharmaceuticalProductionPermitDao.updateYljgzjAndYpscXkStatus(tableName, sqid, udate, flag, errinfo);
    }

    /**
     * 获取药品生产许可表全部数据
     * @return
     */
    @Override
    public List<PharmPduPmtYpscxkQyxk> getYpscxkAllDate() {
        return pharmaceuticalProductionPermitDao.getYpscxkAllDate();
    }

    /**
     * 根据sqid获取单个药品生产许可数据
     * @param sqid
     * @return
     */
    @Override
    public PharmPduPmtYpscxkQyxk getYpscxkAllDateOne(String sqid) {
        return pharmaceuticalProductionPermitDao.getYpscxkAllDateOne(sqid);
    }

    /**
     * 获取药品生产许可变更表全部数据
     * @return
     */
    @Override
    public List<PharmPduPmtYpscxkBg> getYpscxkBgAllDate() {
        return pharmaceuticalProductionPermitDao.getYpscxkBgAllDate();
    }

    /**
     * 获取药品生产企业许可证表
     * @param sqid
     * @return
     */
    @Override
    public PharmPduPmtYpscxkQyxk getYpscQyxkz(String sqid) {
        return pharmaceuticalProductionPermitDao.getYpscQyxkz(sqid);
    }

    /**
     * 获取具备生产条件的生产范围
     * @param sqid
     * @return
     */
    @Override
    public List<PharmPduPmtYpscxkscfw> getYpscJbsctjdscfw(String sqid) {
        return pharmaceuticalProductionPermitDao.getYpscJbsctjdscfw(sqid);
    }

    /**
     * 获取通过境外药品GMP认证
     * @param sqid
     * @return
     */
    @Override
    public List<PharmPduPmtYpscxkGmp> getYpscTgjwypgmprz(String sqid) {
        return pharmaceuticalProductionPermitDao.getYpscTgjwypgmprz(sqid);
    }
}
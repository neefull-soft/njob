package com.xxl.job.executor.service.jobhandler.config;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-04-09 10:26
 **/
public enum PharmProductPermitType {

    PHARM_PRODUCT_PERMIT_TYPE_YLJGZJXK("T_EXC_GJ_YLJGZJXKZXT"),PHARM_PRODUCT_PERMIT_TYPE_TPSCXK("T_API_YPSC_COMPANY_LICENSE"),PHARM_PRODUCT_PERMIT_TYPE_TPSCXKBG("T_API_YPSC_COMPANY_LICENSE");
    private final String name;

    private PharmProductPermitType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
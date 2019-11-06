package com.xxl.job.executor.service.jobhandler.model;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-04-04 10:05
 **/
public class PharmPduPmtYpscxkGmp implements java.io.Serializable{

    private String compId;
    private String authName;
    private String authRange;
    private String passAuthDate;
    private String authOrg;
    private String varietyName;
    private String countryName;
    private String remark;

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthRange() {
        return authRange;
    }

    public void setAuthRange(String authRange) {
        this.authRange = authRange;
    }

    public String getPassAuthDate() {
        return passAuthDate;
    }

    public void setPassAuthDate(String passAuthDate) {
        this.passAuthDate = passAuthDate;
    }

    public String getAuthOrg() {
        return authOrg;
    }

    public void setAuthOrg(String authOrg) {
        this.authOrg = authOrg;
    }

    public String getVarietyName() {
        return varietyName;
    }

    public void setVarietyName(String varietyName) {
        this.varietyName = varietyName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
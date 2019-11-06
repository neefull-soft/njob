package com.xxl.job.executor.service.jobhandler.model;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-04-01 17:11
 **/
public class PharmProductPmtYljgzjxk implements java.io.Serializable{

    private String doType;    //操作类型 1新增，2修改，3删除
    private String licenseNoMarker;   //原许可证号
    private String compName;   //医疗机构名称
    private String orgCode;   //社会信用代码
    private String regAddress;   //注册地址
    private String compType;   //医疗机构类别
    private String licenseNo;   //许可证号
    private String licenseOrg;   //发证机关
    private String issueDate;   //发证日期
    private String expiryDate;   //有效期至
    private String orgName;   //日常监督机构
    private String orgPerson;   //日常监督人员
    private String issuer;   //签发人
    private String licAddrRange;   //配置地址和配置范围（正本）
    private String licAddrRangeF;   //配置地址和配置范围（副本）
    private String legalRepresentative;   //法定代表人
    private String enterpriseHead;   //制剂室负责人
    private String qualityHead;   //质量负责人
    private String provinceAreaName;   //省或直辖市
    private String cityAreaName;   //地级市或直辖市的县区
    private String id;      //申请id，主键

    public String getDoType() {
        return doType;
    }

    public void setDoType(String doType) {
        this.doType = doType;
    }

    public String getLicenseNoMarker() {
        return licenseNoMarker;
    }

    public void setLicenseNoMarker(String licenseNoMarker) {
        this.licenseNoMarker = licenseNoMarker;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }

    public String getCompType() {
        return compType;
    }

    public void setCompType(String compType) {
        this.compType = compType;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getLicenseOrg() {
        return licenseOrg;
    }

    public void setLicenseOrg(String licenseOrg) {
        this.licenseOrg = licenseOrg;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgPerson() {
        return orgPerson;
    }

    public void setOrgPerson(String orgPerson) {
        this.orgPerson = orgPerson;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getLicAddrRange() {
        return licAddrRange;
    }

    public void setLicAddrRange(String licAddrRange) {
        this.licAddrRange = licAddrRange;
    }

    public String getLicAddrRangeF() {
        return licAddrRangeF;
    }

    public void setLicAddrRangeF(String licAddrRangeF) {
        this.licAddrRangeF = licAddrRangeF;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getEnterpriseHead() {
        return enterpriseHead;
    }

    public void setEnterpriseHead(String enterpriseHead) {
        this.enterpriseHead = enterpriseHead;
    }

    public String getQualityHead() {
        return qualityHead;
    }

    public void setQualityHead(String qualityHead) {
        this.qualityHead = qualityHead;
    }

    public String getProvinceAreaName() {
        return provinceAreaName;
    }

    public void setProvinceAreaName(String provinceAreaName) {
        this.provinceAreaName = provinceAreaName;
    }

    public String getCityAreaName() {
        return cityAreaName;
    }

    public void setCityAreaName(String cityAreaName) {
        this.cityAreaName = cityAreaName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
package com.xxl.job.executor.service.jobhandler.model;

import com.xxl.job.executor.service.jobhandler.annotation.XmlElementAnno;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 国产非特殊用途化妆品备案信息 bean
 */
@XmlRootElement(name = "row")
public class GCFTSYTHZPBAXXModel extends BaseModel {
    @XmlElement
    @XmlElementAnno
    private String hzpscqymcid;

    @XmlElement
    @XmlElementAnno
    private String cpmc;

    @XmlElement
    @XmlElementAnno
    private String babh;

    @XmlElement
    @XmlElementAnno
    private String barq;

    @XmlElement
    @XmlElementAnno
    private String scqy;

    @XmlElement
    @XmlElementAnno
    private String scqydz;

    @XmlElement
    @XmlElementAnno
    private String sjscqymc;

    @XmlElement
    @XmlElementAnno
    private String sjscqydz;

    @XmlElement
    @XmlElementAnno
    private String scxkzbh;

    @XmlElement
    @XmlElementAnno
    private String sm;

    @XmlElement
    @XmlElementAnno
    private String cpbzpmt;

    @XmlElement
    @XmlElementAnno
    private String cpbzltt;

    @XmlElement
    @XmlElementAnno
    private String ppzt;

    @XmlElement
    @XmlElementAnno
    private String bglsjl;

    @XmlElement
    @XmlElementAnno
    private String bz;

    @XmlElement
    @XmlElementAnno
    private String cfhzp;

    @XmlElement
    @XmlElementAnno
    private String sjzhxgsj;

    public String getHzpscqymcid() {
        return hzpscqymcid;
    }

    public void setHzpscqymcid(String hzpscqymcid) {
        this.hzpscqymcid = hzpscqymcid;
    }

    public String getCpmc() {
        return cpmc;
    }

    public void setCpmc(String cpmc) {
        this.cpmc = cpmc;
    }

    public String getBabh() {
        return babh;
    }

    public void setBabh(String babh) {
        this.babh = babh;
    }

    public String getBarq() {
        return barq;
    }

    public void setBarq(String barq) {
        this.barq = barq;
    }

    public String getScqy() {
        return scqy;
    }

    public void setScqy(String scqy) {
        this.scqy = scqy;
    }

    public String getScqydz() {
        return scqydz;
    }

    public void setScqydz(String scqydz) {
        this.scqydz = scqydz;
    }

    public String getSjscqymc() {
        return sjscqymc;
    }

    public void setSjscqymc(String sjscqymc) {
        this.sjscqymc = sjscqymc;
    }

    public String getSjscqydz() {
        return sjscqydz;
    }

    public void setSjscqydz(String sjscqydz) {
        this.sjscqydz = sjscqydz;
    }

    public String getScxkzbh() {
        return scxkzbh;
    }

    public void setScxkzbh(String scxkzbh) {
        this.scxkzbh = scxkzbh;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getCpbzpmt() {
        return cpbzpmt;
    }

    public void setCpbzpmt(String cpbzpmt) {
        this.cpbzpmt = cpbzpmt;
    }

    public String getCpbzltt() {
        return cpbzltt;
    }

    public void setCpbzltt(String cpbzltt) {
        this.cpbzltt = cpbzltt;
    }

    public String getPpzt() {
        return ppzt;
    }

    public void setPpzt(String ppzt) {
        this.ppzt = ppzt;
    }

    public String getBglsjl() {
        return bglsjl;
    }

    public void setBglsjl(String bglsjl) {
        this.bglsjl = bglsjl;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getCfhzp() {
        return cfhzp;
    }

    public void setCfhzp(String cfhzp) {
        this.cfhzp = cfhzp;
    }

    public String getSjzhxgsj() {
        return sjzhxgsj;
    }

    public void setSjzhxgsj(String sjzhxgsj) {
        this.sjzhxgsj = sjzhxgsj;
    }
}

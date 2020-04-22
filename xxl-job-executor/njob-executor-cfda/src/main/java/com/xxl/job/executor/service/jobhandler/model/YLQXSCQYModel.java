package com.xxl.job.executor.service.jobhandler.model;

import com.xxl.job.executor.service.jobhandler.annotation.XmlElementAnno;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 医疗器械生产企业 Bean
 */
@XmlRootElement(name = "row")
public class YLQXSCQYModel extends BaseModel {

    @XmlElement
    @XmlElementAnno
    private String sjzhxgsj;

    @XmlElement
    @XmlElementAnno
    private String zzjgdm;

    @XmlElement
    @XmlElementAnno
    private String ylqxscxkzh;

    @XmlElement
    @XmlElementAnno
    private String yxkzbh;

    @XmlElement
    @XmlElementAnno
    private String qymc;

    @XmlElement
    @XmlElementAnno
    private String frdb;

    @XmlElement
    @XmlElementAnno
    private String zcdz;

    @XmlElement
    @XmlElementAnno
    private String scdzclob;

    @XmlElement
    @XmlElementAnno
    private String qxqyscfw;

    @XmlElement
    @XmlElementAnno
    private String qyfzrxm;

    @XmlElement
    @XmlElementAnno
    private String fzjg;

    @XmlElement
    @XmlElementAnno
    private String fzrq;

    @XmlElement
    @XmlElementAnno
    private String zjzsyxqzzrq;

    @XmlElement
    @XmlElementAnno
    private String dzsqzzzm;

    @XmlElement
    @XmlElementAnno
    private String bglsjl;

    @XmlElement
    @XmlElementAnno
    private String ppzt;

    @XmlElement
    @XmlElementAnno
    private String ztbzyy;

    @XmlElement
    @XmlElementAnno
    private String ztbzsj;

    @XmlElement
    @XmlElementAnno
    private String id;

    public String getSjzhxgsj() {
        return sjzhxgsj;
    }

    public void setSjzhxgsj(String sjzhxgsj) {
        this.sjzhxgsj = sjzhxgsj;
    }

    public String getZzjgdm() {
        return zzjgdm;
    }

    public void setZzjgdm(String zzjgdm) {
        this.zzjgdm = zzjgdm;
    }

    public String getYlqxscxkzh() {
        return ylqxscxkzh;
    }

    public void setYlqxscxkzh(String ylqxscxkzh) {
        this.ylqxscxkzh = ylqxscxkzh;
    }

    public String getYxkzbh() {
        return yxkzbh;
    }

    public void setYxkzbh(String yxkzbh) {
        this.yxkzbh = yxkzbh;
    }

    public String getQymc() {
        return qymc;
    }

    public void setQymc(String qymc) {
        this.qymc = qymc;
    }

    public String getFrdb() {
        return frdb;
    }

    public void setFrdb(String frdb) {
        this.frdb = frdb;
    }

    public String getZcdz() {
        return zcdz;
    }

    public void setZcdz(String zcdz) {
        this.zcdz = zcdz;
    }

    public String getScdzclob() {
        return scdzclob;
    }

    public void setScdzclob(String scdzclob) {
        this.scdzclob = scdzclob;
    }

    public String getQxqyscfw() {
        return qxqyscfw;
    }

    public void setQxqyscfw(String qxqyscfw) {
        this.qxqyscfw = qxqyscfw;
    }

    public String getQyfzrxm() {
        return qyfzrxm;
    }

    public void setQyfzrxm(String qyfzrxm) {
        this.qyfzrxm = qyfzrxm;
    }

    public String getFzjg() {
        return fzjg;
    }

    public void setFzjg(String fzjg) {
        this.fzjg = fzjg;
    }

    public String getFzrq() {
        return fzrq;
    }

    public void setFzrq(String fzrq) {
        this.fzrq = fzrq;
    }

    public String getZjzsyxqzzrq() {
        return zjzsyxqzzrq;
    }

    public void setZjzsyxqzzrq(String zjzsyxqzzrq) {
        this.zjzsyxqzzrq = zjzsyxqzzrq;
    }

    public String getDzsqzzzm() {
        return dzsqzzzm;
    }

    public void setDzsqzzzm(String dzsqzzzm) {
        this.dzsqzzzm = dzsqzzzm;
    }

    public String getBglsjl() {
        return bglsjl;
    }

    public void setBglsjl(String bglsjl) {
        this.bglsjl = bglsjl;
    }

    public String getPpzt() {
        return ppzt;
    }

    public void setPpzt(String ppzt) {
        this.ppzt = ppzt;
    }

    public String getZtbzyy() {
        return ztbzyy;
    }

    public void setZtbzyy(String ztbzyy) {
        this.ztbzyy = ztbzyy;
    }

    public String getZtbzsj() {
        return ztbzsj;
    }

    public void setZtbzsj(String ztbzsj) {
        this.ztbzsj = ztbzsj;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

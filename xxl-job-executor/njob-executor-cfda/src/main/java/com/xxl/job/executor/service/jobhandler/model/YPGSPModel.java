package com.xxl.job.executor.service.jobhandler.model;

import com.xxl.job.executor.service.jobhandler.annotation.XmlElementAnno;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 药品GP认证
 */
@XmlRootElement(name = "row")
public class YPGSPModel extends BaseModel {
    @XmlElement
    @XmlElementAnno
    private String sjzhxgsj;
    @XmlElement
    @XmlElementAnno
    private String gspzsbh;
    @XmlElement
    @XmlElementAnno
    private String qymc;
    @XmlElement
    @XmlElementAnno
    private String rzfw;
    @XmlElement
    @XmlElementAnno
    private String jydz;
    @XmlElement
    @XmlElementAnno
    private String fzqr;
    @XmlElement
    @XmlElementAnno
    private String zjzsyxqzzrq;
    @XmlElement
    @XmlElementAnno
    private String fzjg;
    @XmlElement
    @XmlElementAnno
    private String ppzt;
    @XmlElement
    @XmlElementAnno
    private String ztbzyy;
    @XmlElement
    @XmlElementAnno
    private String ztbzsj;

    public String getSjzhxgsj() {
        return sjzhxgsj;
    }

    public void setSjzhxgsj(String sjzhxgsj) {
        this.sjzhxgsj = sjzhxgsj;
    }

    public String getGspzsbh() {
        return gspzsbh;
    }

    public void setGspzsbh(String gspzsbh) {
        this.gspzsbh = gspzsbh;
    }

    public String getQymc() {
        return qymc;
    }

    public void setQymc(String qymc) {
        this.qymc = qymc;
    }

    public String getRzfw() {
        return rzfw;
    }

    public void setRzfw(String rzfw) {
        this.rzfw = rzfw;
    }

    public String getJydz() {
        return jydz;
    }

    public void setJydz(String jydz) {
        this.jydz = jydz;
    }

    public String getFzqr() {
        return fzqr;
    }

    public void setFzqr(String fzqr) {
        this.fzqr = fzqr;
    }

    public String getZjzsyxqzzrq() {
        return zjzsyxqzzrq;
    }

    public void setZjzsyxqzzrq(String zjzsyxqzzrq) {
        this.zjzsyxqzzrq = zjzsyxqzzrq;
    }

    public String getFzjg() {
        return fzjg;
    }

    public void setFzjg(String fzjg) {
        this.fzjg = fzjg;
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
}

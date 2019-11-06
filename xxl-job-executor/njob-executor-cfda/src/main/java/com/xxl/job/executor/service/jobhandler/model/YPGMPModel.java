package com.xxl.job.executor.service.jobhandler.model;

import com.xxl.job.executor.service.jobhandler.annotation.XmlElementAnno;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 药品GMP认证
 */
@XmlRootElement(name = "row")
public class YPGMPModel extends BaseModel {
    @XmlElement
    @XmlElementAnno
    private String sjzhxgsj;
    @XmlElement
    @XmlElementAnno
    private String ypgmpzsbh;
    @XmlElement
    @XmlElementAnno
    private String qymc;
    @XmlElement
    @XmlElementAnno
    private String qymcyw;
    @XmlElement
    @XmlElementAnno
    private String scdzclob;
    @XmlElement
    @XmlElementAnno
    private String scdzywclob;
    @XmlElement
    @XmlElementAnno
    private String rzfw;
    @XmlElement
    @XmlElementAnno
    private String rzfwyw;
    @XmlElement
    @XmlElementAnno
    private String ggrq;
    @XmlElement
    @XmlElementAnno
    private String zjzsyxqzzrq;
    @XmlElement
    @XmlElementAnno
    private String pzyxrq;
    @XmlElement
    @XmlElementAnno
    private String yxqyxrq;
    @XmlElement
    @XmlElementAnno
    private String fzjg;
    @XmlElement
    @XmlElementAnno
    private String pzyxrzfw;
    @XmlElement
    @XmlElementAnno
    private String pzyxrzfwyw;
    @XmlElement
    @XmlElementAnno
    private String gmpbb;
    @XmlElement
    @XmlElementAnno
    private String bz;
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

    public String getYpgmpzsbh() {
        return ypgmpzsbh;
    }

    public void setYpgmpzsbh(String ypgmpzsbh) {
        this.ypgmpzsbh = ypgmpzsbh;
    }

    public String getQymc() {
        return qymc;
    }

    public void setQymc(String qymc) {
        this.qymc = qymc;
    }

    public String getQymcyw() {
        return qymcyw;
    }

    public void setQymcyw(String qymcyw) {
        this.qymcyw = qymcyw;
    }

    public String getScdzclob() {
        return scdzclob;
    }

    public void setScdzclob(String scdzclob) {
        this.scdzclob = scdzclob;
    }

    public String getScdzywclob() {
        return scdzywclob;
    }

    public void setScdzywclob(String scdzywclob) {
        this.scdzywclob = scdzywclob;
    }

    public String getRzfw() {
        return rzfw;
    }

    public void setRzfw(String rzfw) {
        this.rzfw = rzfw;
    }

    public String getRzfwyw() {
        return rzfwyw;
    }

    public void setRzfwyw(String rzfwyw) {
        this.rzfwyw = rzfwyw;
    }

    public String getGgrq() {
        return ggrq;
    }

    public void setGgrq(String ggrq) {
        this.ggrq = ggrq;
    }

    public String getZjzsyxqzzrq() {
        return zjzsyxqzzrq;
    }

    public void setZjzsyxqzzrq(String zjzsyxqzzrq) {
        this.zjzsyxqzzrq = zjzsyxqzzrq;
    }

    public String getPzyxrq() {
        return pzyxrq;
    }

    public void setPzyxrq(String pzyxrq) {
        this.pzyxrq = pzyxrq;
    }

    public String getYxqyxrq() {
        return yxqyxrq;
    }

    public void setYxqyxrq(String yxqyxrq) {
        this.yxqyxrq = yxqyxrq;
    }

    public String getFzjg() {
        return fzjg;
    }

    public void setFzjg(String fzjg) {
        this.fzjg = fzjg;
    }

    public String getPzyxrzfw() {
        return pzyxrzfw;
    }

    public void setPzyxrzfw(String pzyxrzfw) {
        this.pzyxrzfw = pzyxrzfw;
    }

    public String getPzyxrzfwyw() {
        return pzyxrzfwyw;
    }

    public void setPzyxrzfwyw(String pzyxrzfwyw) {
        this.pzyxrzfwyw = pzyxrzfwyw;
    }

    public String getGmpbb() {
        return gmpbb;
    }

    public void setGmpbb(String gmpbb) {
        this.gmpbb = gmpbb;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
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

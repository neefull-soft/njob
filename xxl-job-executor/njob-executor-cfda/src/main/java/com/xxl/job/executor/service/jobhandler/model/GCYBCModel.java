package com.xxl.job.executor.service.jobhandler.model;

import com.xxl.job.executor.service.jobhandler.annotation.XmlElementAnno;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 国产药包材实体Bean
 */
@XmlRootElement(name = "row")
public class GCYBCModel extends BaseModel {
    @XmlElement
    @XmlElementAnno
    private String sjzhxgsj;
    @XmlElement
    @XmlElementAnno
    private String yptymc;
    @XmlElement
    @XmlElementAnno
    private String ypgg;
    @XmlElement
    @XmlElementAnno
    private String pzrq;
    @XmlElement
    @XmlElementAnno
    private String yppzwh;
    @XmlElement
    @XmlElementAnno
    private String yppzwhyxq;
    @XmlElement
    @XmlElementAnno
    private String ypscqymc;
    @XmlElement
    @XmlElementAnno
    private String scdz;
    @XmlElement
    @XmlElementAnno
    private String ypspjl01;
    @XmlElement
    @XmlElementAnno
    private String pzzt;
    @XmlElement
    @XmlElementAnno
    private String ztbzsj;
    @XmlElement
    @XmlElementAnno
    private String ztbzyy;

    public String getSjzhxgsj() {
        return sjzhxgsj;
    }

    public void setSjzhxgsj(String sjzhxgsj) {
        this.sjzhxgsj = sjzhxgsj;
    }

    public String getYptymc() {
        return yptymc;
    }

    public void setYptymc(String yptymc) {
        this.yptymc = yptymc;
    }

    public String getYpgg() {
        return ypgg;
    }

    public void setYpgg(String ypgg) {
        this.ypgg = ypgg;
    }

    public String getPzrq() {
        return pzrq;
    }

    public void setPzrq(String pzrq) {
        this.pzrq = pzrq;
    }

    public String getYppzwh() {
        return yppzwh;
    }

    public void setYppzwh(String yppzwh) {
        this.yppzwh = yppzwh;
    }

    public String getYppzwhyxq() {
        return yppzwhyxq;
    }

    public void setYppzwhyxq(String yppzwhyxq) {
        this.yppzwhyxq = yppzwhyxq;
    }

    public String getYpscqymc() {
        return ypscqymc;
    }

    public void setYpscqymc(String ypscqymc) {
        this.ypscqymc = ypscqymc;
    }

    public String getScdz() {
        return scdz;
    }

    public void setScdz(String scdz) {
        this.scdz = scdz;
    }

    public String getYpspjl01() {
        return ypspjl01;
    }

    public void setYpspjl01(String ypspjl01) {
        this.ypspjl01 = ypspjl01;
    }

    public String getPzzt() {
        return pzzt;
    }

    public void setPzzt(String pzzt) {
        this.pzzt = pzzt;
    }

    public String getZtbzsj() {
        return ztbzsj;
    }

    public void setZtbzsj(String ztbzsj) {
        this.ztbzsj = ztbzsj;
    }

    public String getZtbzyy() {
        return ztbzyy;
    }

    public void setZtbzyy(String ztbzyy) {
        this.ztbzyy = ztbzyy;
    }
}

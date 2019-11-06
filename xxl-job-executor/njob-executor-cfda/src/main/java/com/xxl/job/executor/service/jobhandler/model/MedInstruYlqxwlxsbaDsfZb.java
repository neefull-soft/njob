package com.xxl.job.executor.service.jobhandler.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-08-29 16:48
 **/
@XStreamAlias("subDataNode")
public class MedInstruYlqxwlxsbaDsfZb implements java.io.Serializable{

    private String SHXYDM;
    private String DSFPTBAFPTMC;
    private String PZBH;
    private String BZ;

    public String getSHXYDM() {
        return SHXYDM;
    }

    public void setSHXYDM(String SHXYDM) {
        this.SHXYDM = SHXYDM;
    }

    public String getDSFPTBAFPTMC() {
        return DSFPTBAFPTMC;
    }

    public void setDSFPTBAFPTMC(String DSFPTBAFPTMC) {
        this.DSFPTBAFPTMC = DSFPTBAFPTMC;
    }

    public String getPZBH() {
        return PZBH;
    }

    public void setPZBH(String PZBH) {
        this.PZBH = PZBH;
    }

    public String getBZ() {
        return BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
    }
}
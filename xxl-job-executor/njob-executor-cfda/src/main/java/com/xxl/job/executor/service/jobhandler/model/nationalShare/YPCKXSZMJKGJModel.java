package com.xxl.job.executor.service.jobhandler.model.nationalShare;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("subDataNode")
public class YPCKXSZMJKGJModel implements java.io.Serializable{

    private String ZSBHZW;
    private String JKGZW;
    private String JKGYW;
    private String CKSL;

    public String getZSBHZW() {
        return ZSBHZW;
    }

    public void setZSBHZW(String ZSBHZW) {
        this.ZSBHZW = ZSBHZW;
    }

    public String getJKGZW() {
        return JKGZW;
    }

    public void setJKGZW(String JKGZW) {
        this.JKGZW = JKGZW;
    }

    public String getJKGYW() {
        return JKGYW;
    }

    public void setJKGYW(String JKGYW) {
        this.JKGYW = JKGYW;
    }

    public String getCKSL() {
        return CKSL;
    }

    public void setCKSL(String CKSL) {
        this.CKSL = CKSL;
    }
}

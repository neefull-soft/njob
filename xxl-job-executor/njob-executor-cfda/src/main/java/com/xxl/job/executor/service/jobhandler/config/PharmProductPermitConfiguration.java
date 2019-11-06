package com.xxl.job.executor.service.jobhandler.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-04-02 14:44
 **/
@Configuration
public class PharmProductPermitConfiguration implements InitializingBean {

    private static PharmProductPermitConfiguration pppConfig = null;

    @Value("${pharmpdutpit.yljgzjxk.url}")
    private String yljgzjxkUrl;
    @Value("${pharmpdutpit.ypscxk.url}")
    private String ypscxkUrl;
    @Value("${pharmpdutpit.ypscxkchange.url}")
    private String ypscxkChangeUrl;
    @Value("${pharmpdutpit.send.account}")
    private String sendAccount;
    @Value("${pharmpdutpit.send.pwd}")
    private String sendPwd;

    public static PharmProductPermitConfiguration getAdminConfig() {
        return pppConfig;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        pppConfig = this;
    }

    public String getYljgzjxkUrl() {
        return yljgzjxkUrl;
    }

    public void setYljgzjxkUrl(String yljgzjxkUrl) {
        this.yljgzjxkUrl = yljgzjxkUrl;
    }

    public String getYpscxkUrl() {
        return ypscxkUrl;
    }

    public void setYpscxkUrl(String ypscxkUrl) {
        this.ypscxkUrl = ypscxkUrl;
    }

    public String getSendAccount() {
        return sendAccount;
    }

    public void setSendAccount(String sendAccount) {
        this.sendAccount = sendAccount;
    }

    public String getSendPwd() {
        return sendPwd;
    }

    public void setSendPwd(String sendPwd) {
        this.sendPwd = sendPwd;
    }

    public String getYpscxkChangeUrl() {
        return ypscxkChangeUrl;
    }

    public void setYpscxkChangeUrl(String ypscxkChangeUrl) {
        this.ypscxkChangeUrl = ypscxkChangeUrl;
    }
}
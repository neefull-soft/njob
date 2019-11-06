package com.xxl.job.executor.service.jobhandler.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-08-27 16:53
 **/
@Configuration
public class MedicalInstrumentsConfiguration implements InitializingBean {

    private static MedicalInstrumentsConfiguration pppConfig = null;

    @Value("${medicalinstru.ylqxscxk.url}")
    private String ylqxscxkUrl;
    @Value("${medicalinstru.username}")
    private String username;
    @Value("${medicalinstru.password}")
    private String password;
    @Value("${medicalinstru.ylqxscqyjc.url}")
    private String ylqxscqyjcUrl;
    @Value("${medicalinstru.ylqxscqyjc.loginid}")
    private String loginid;
    @Value("${medicalinstru.ylqxscqyjc.scqyjcPassword}")
    private String scqyjcPassword;
    @Value("${medicalinstru.clientkeystore.path}")
    private String clientKeystorePath;

    public static MedicalInstrumentsConfiguration getAdminConfig() {
        return pppConfig;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        pppConfig = this;
    }

    public String getYlqxscxkUrl() {
        return ylqxscxkUrl;
    }

    public void setYlqxscxkUrl(String ylqxscxkUrl) {
        this.ylqxscxkUrl = ylqxscxkUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getYlqxscqyjcUrl() {
        return ylqxscqyjcUrl;
    }

    public void setYlqxscqyjcUrl(String ylqxscqyjcUrl) {
        this.ylqxscqyjcUrl = ylqxscqyjcUrl;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getScqyjcPassword() {
        return scqyjcPassword;
    }

    public void setScqyjcPassword(String scqyjcPassword) {
        this.scqyjcPassword = scqyjcPassword;
    }

    public String getClientKeystorePath() {
        return clientKeystorePath;
    }

    public void setClientKeystorePath(String clientKeystorePath) {
        this.clientKeystorePath = clientKeystorePath;
    }
}
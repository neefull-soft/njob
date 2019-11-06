package com.xxl.job.executor.service.jobhandler.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedicalDeviceConfiguration implements InitializingBean {

    private static MedicalDeviceConfiguration ylqxConfig = null;
    @Value("${ylqx.token.url}")
    private String tokenUrl;
    @Value("${ylqx.token.client_id}")
    private String clientId;
    @Value("${ylqx.token.client_secret}")
    private String clientSecret;
    @Value("${ylqx.token.redirect_uri}")
    private String rediretUrl;
    @Value("${ylqx.token.grant_type}")
    private String grantType;
    @Value("${ylqx.token.state}")
    private String state;
    @Value("${ylqx.data.url}")
    private String dataUrl;

    public static MedicalDeviceConfiguration getAdminConfig() {
        return ylqxConfig;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ylqxConfig = this;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRediretUrl() {
        return rediretUrl;
    }

    public void setRediretUrl(String rediretUrl) {
        this.rediretUrl = rediretUrl;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}

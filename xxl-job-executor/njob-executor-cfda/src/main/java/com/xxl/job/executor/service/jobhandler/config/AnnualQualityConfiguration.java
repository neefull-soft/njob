package com.xxl.job.executor.service.jobhandler.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-10-09 15:19
 **/
@Configuration
public class AnnualQualityConfiguration implements InitializingBean {

    private static AnnualQualityConfiguration pppConfig = null;

    @Value("${ylqxscqy.ndzltx.zcbg.url}")
    private String ndzltxZcbgUrl;
    @Value("${ylqxscqy.ndzltx.jjxxzd.url}")
    private String ndzltxJjxxzdUrl;
    @Value("${ylqxscqy.ndzltx.zcbg.pdfFile.puffx}")
    private String zcbgPdfFileUrl;

    public static AnnualQualityConfiguration getAdminConfig() {
        return pppConfig;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        pppConfig = this;
    }

    public String getNdzltxZcbgUrl() {
        return ndzltxZcbgUrl;
    }

    public void setNdzltxZcbgUrl(String ndzltxZcbgUrl) {
        this.ndzltxZcbgUrl = ndzltxZcbgUrl;
    }

    public String getNdzltxJjxxzdUrl() {
        return ndzltxJjxxzdUrl;
    }

    public void setNdzltxJjxxzdUrl(String ndzltxJjxxzdUrl) {
        this.ndzltxJjxxzdUrl = ndzltxJjxxzdUrl;
    }

    public String getZcbgPdfFileUrl() {
        return zcbgPdfFileUrl;
    }

    public void setZcbgPdfFileUrl(String zcbgPdfFileUrl) {
        this.zcbgPdfFileUrl = zcbgPdfFileUrl;
    }
}
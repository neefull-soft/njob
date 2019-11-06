package com.xxl.job.executor.service.jobhandler.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CFDAConfiguration implements InitializingBean {

    private static CFDAConfiguration cfdaConfig = null;
    @Value("${cfda.share.url}")
    private String url;
    @Value("${cfda.username}")
    private String username;
    @Value("${cfda.password}")
    private String password;

    public static CFDAConfiguration getAdminConfig() {
        return cfdaConfig;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        cfdaConfig = this;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    /**
     * 生成soap请求的xml，生成的具体消息内容，可根据SOAPUI根据获得
     *
     * @param presentDate 最新的日期
     * @param dbName      数据库名   比如国产药品，具体对应到国家局网站公示的信息
     * @return
     */
    public String getSoapReqXml(String presentDate, String dbName) {
        String requestStr = "<?xml version = \"1.0\" encoding=\"UTF-8\"?>\n" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.datashare.medicom.com/\">\n" +
                "<soapenv:Header/>\n" +
                "<soapenv:Body>\n" +
                "<web:CFDA_CG_DATADOWN>\n" +
                "<presentDate>" + presentDate + "</presentDate>\n" +
                "<dbName>" + dbName + "</dbName>\n" +
                "<account>" + username + "</account>\n" +
                "<pwd>" + password + "</pwd>\n" +
                "</web:CFDA_CG_DATADOWN>\n" +
                "</soapenv:Body>\n" +
                "</soapenv:Envelope>";
        return requestStr;
    }

}

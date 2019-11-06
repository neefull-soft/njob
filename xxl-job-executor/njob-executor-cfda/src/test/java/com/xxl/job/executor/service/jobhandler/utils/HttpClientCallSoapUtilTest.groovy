package com.xxl.job.executor.service.jobhandler.utils

import junit.framework.TestCase

import javax.xml.soap.SOAPBody
import javax.xml.soap.SOAPElement
import javax.xml.soap.SOAPMessage

class HttpClientCallSoapUtilTest extends TestCase {
    void testDoPostSoap1_1() {

        final String requestStr = CFDAConfig.getAdminConfig().getSoapReqXml("2018-11-01", "国产药品");
        String postUrl = "http://10.64.1.135/webservices/dataShare";
        //采用SOAP1.1调用服务端，这种方式能调用服务端为soap1.1和soap1.2的服务
        String respStr = doPostSoap1_1(postUrl, requestStr, "");
        SOAPMessage msg = formatSoapString(respStr);
        SOAPBody body = msg.getSOAPBody();
        Iterator<SOAPElement> iterator = body.getChildElements();
        String result = getResult(iterator, "result");

        List<Object> jaBeanToXml = null;
        try {
            jaBeanToXml = Xml4BeanUtils.parseXmlToBeanList(result, "row", GCYP.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.xxl.job.executor.service.jobhandler.utils;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: xxl-job
 * @author: xi.chen
 * @create: 2019-08-29 13:10
 **/
public class CallXmlUtils {

    private static final Logger log = LoggerFactory.getLogger(CallXmlUtils.class);

    /**
     * 医疗器械生产许可请求webservice
     * @param url
     * @param dbName
     * @param account
     * @param pwd
     * @param xmlStr
     * @return
     */
    public static String sendCallRequest(String url,String dbName,String account,String pwd,String xmlStr){
        String response = "";
        try {
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(url);
            //命名空间和调用接口的方法名
            call.setOperationName(new javax.xml.namespace.QName("http://impl.dataHandle.medicom.com/", "CFDA_CG_DATACOL"));
            call.setUseSOAPAction(true);
            call.addParameter("arg3",org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);//可多个.addParameterMode
            call.addParameter("arg2",org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);//可多个.addParameterMode
            call.addParameter("arg1",org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);//可多个.addParameterMode
            call.addParameter("arg0",org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);//可多个.addParameterMode
            // 设置返回类型
            call.setReturnClass(String.class);
            // 使用invoke调用方法，Object数据放传入的参数值（可多个）
            response = (String) call.invoke(new Object[] {dbName, account,pwd,xmlStr});
            System.out.println(response);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return response;
    }

    /**
     * 医疗器械生产企业检查发送请求
     * @param url
     * @param jsonParam
     * @return
     */
    public static String sendYlqxscqyjc(String url,String jsonParam,String clientKeystore){
        String result = "";
        try {
            System.setProperty("javax.net.ssl.trustStore",clientKeystore);  //客户端证书路径
            System.setProperty("javax.net.ssl.trustStorePassword","tongweb");   //密码
            System.setProperty("java.protocol.handler.pkgs","com.sun.net.ssl.internal.www.protocol");
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(url);
            call.setOperationName("sendRwxx");
            call.addParameter("json",org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
            result = (String) call.invoke(new Object[]{jsonParam});
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
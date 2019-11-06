package com.xxl.job.executor.service.jobhandler.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.xxl.job.executor.service.jobhandler.annotation.XmlElementAnno;
import com.xxl.job.executor.service.jobhandler.model.BaseModel;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * xml字符串和Java Bean互转
 */
public class Xml4BeanUtils {
    private final static Logger log = Logger.getLogger(Xml4BeanUtils.class);

    /**
     * 将javaBean转换为xml对象
     *
     * @param clazz
     * @param bean
     * @return
     */
    public static String parseBeanToXml(Class clazz, Object bean) {
        StringWriter sw = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            sw = new StringWriter();
            //该值默认为false,true则不会创建即头信息,即<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            jaxbMarshaller.marshal(bean, sw);
        } catch (JAXBException e) {
            log.error(e.getMessage());
        }
        return sw.toString();
    }

    /**
     * 将javaBean转换为xml对象
     * @param bean
     * @return
     */
    public static String parseBeanToXmlV2(Object bean){
        XStream xstream = new XStream(new DomDriver("utf-8"));
        xstream.processAnnotations(bean.getClass());
        return xstream.toXML(bean);
    }

    /**
     * 将xml对象转换为javaBean列表
     *
     * @param clazz
     * @param xml
     * @return
     */
    public static List<BaseModel> parseXmlToBeanList(String xml, String childTagName, Class clazz) throws IOException {
        List beanList = new ArrayList();
        if (xml != null && xml != "") {
            Field[] fields = clazz.getDeclaredFields();
            List<Field> fieldList = new ArrayList<Field>();
            for (Field fie : fields) {
                if (fie.isAnnotationPresent(XmlElementAnno.class)) {
                    fieldList.add(fie);
                }
            }
            try {
                StringReader read = new StringReader(xml);
                InputSource source = new InputSource(read);
                //创建一个新的SAXBuilder
                SAXReader sr = new SAXReader();
                Document doc = sr.read(source);
                //取的根元素
                Element root = doc.getRootElement();
                List<Element> lEls = root.elements(childTagName);
                //循环每个值节点
                for (Element e : lEls) {
                    Object object = clazz.newInstance();
                    if (!fieldList.isEmpty()) {
                        for (Field field : fieldList) {
                            Element child = e.element(field.getName().toUpperCase());
                            if (child != null) {
                                BeanUtils.setProperty(object, field.getName(), child.getStringValue());
                            }
                        }
                    }
                    beanList.add(object);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }

        }
        return beanList;
    }
}

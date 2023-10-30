package com.whg.iSpring.ch01.v1;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassPathXmlApplicationContext {

    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    private final Map<String, Object> singletonMap = new HashMap<>();

    public ClassPathXmlApplicationContext(String fileName){
        try {
            readXml(fileName);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void readXml(String fileName) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        URL xmlPath = getClass().getClassLoader().getResource(fileName);
        Document document = saxReader.read(xmlPath);
        Element rootElement = document.getRootElement();
        for(Element element:(List<Element>)rootElement.elements()){
            String id = element.attributeValue("id");
            String className = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(id, className);
            beanDefinitionMap.put(id, beanDefinition);
        }
    }

    public Object getBean(String id){
        Object bean = singletonMap.get(id);
        if(bean != null){
            return bean;
        }

        BeanDefinition beanDefinition = beanDefinitionMap.get(id);
        if (beanDefinition == null) {
            return null;
        }

        try {
            bean = Class.forName(beanDefinition.getClassName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        singletonMap.put(id, bean);
        return bean;
    }

}

package com.whg.iSpring.ch02.v2;

import org.dom4j.Element;

public class XmlBeanDefinitionReader {

    private final SimpleBeanFactory beanFactory;

    public XmlBeanDefinitionReader(SimpleBeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }

    public void loadBeanDefinition(BeanResource beanResource){
        while(beanResource.hasNext()){
            Element element = (Element) beanResource.next();
            String id = element.attributeValue("id");
            String className = element.attributeValue("class");
            beanFactory.registerBeanDefinition(new BeanDefinition(id, className));
        }
    }

}

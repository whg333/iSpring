package com.whg.iSpring.ch03.v2.bean;

import com.whg.iSpring.ch03.v2.definition.SimpleBeanFactory;
import com.whg.iSpring.ch03.v2.definition.XmlBeanDefinitionReader;

public class ClassPathXmlApplicationContext implements BeanFactory{

    private final SimpleBeanFactory beanFactory;

    public ClassPathXmlApplicationContext(SimpleBeanFactory beanFactory, String fileName){
        this.beanFactory = beanFactory;
        BeanResource resource = new ClassPathXmlResource(fileName); // 读取xml
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinition(resource); //遍历去解析读取xml后的内容，构建bean定义并注册到beanFactory
        beanFactory.refresh();
    }

    @Override
    public void registerBean(String id, Object beanObj) {
        beanFactory.registerBean(id, beanObj);
    }

    @Override
    public Object getBean(String id){
        return beanFactory.getBean(id);
    }

}

package com.whg.iSpring.ch03.v2;

import com.whg.iSpring.ch03.v2.definition.SimpleBeanFactory;
import com.whg.iSpring.ch03.v2.definition.XmlBeanDefinitionReader;

public class ClassPathXmlApplicationContext implements BeanFactory{

    private final SimpleBeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String fileName){
        beanFactory = new SimpleBeanFactory();
        BeanResource resource = new ClassPathXmlResource(fileName); // 解析xml
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinition(resource); //遍历解析xml后的内容，构建bean定义并注册到beanFactory
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

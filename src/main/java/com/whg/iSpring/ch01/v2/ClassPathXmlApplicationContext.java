package com.whg.iSpring.ch01.v2;

import com.whg.iSpring.ch01.BeanDefinition;

public class ClassPathXmlApplicationContext implements BeanFactory{

    private final BeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String fileName){
        beanFactory = new SimpleBeanFactory();
        BeanResource resource = new ClassPathXmlResource(fileName); // 解析xml
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinition(resource); //遍历解析xml后的内容，构建bean定义并注册到beanFactory
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanFactory.registerBeanDefinition(beanDefinition);
    }

    @Override
    public Object getBean(String id){
        return beanFactory.getBean(id);
    }

}

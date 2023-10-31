package com.whg.iSpring.ch03.v2;

import java.util.concurrent.ConcurrentHashMap;

public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    private final ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        String id = beanDefinition.getId();
        if (beanDefinitionMap.put(id, beanDefinition) != null) {
            throw new BeanException(String.format("id为%s的Bean重复定义", id));
        }
    }

    @Override
    public void registerBean(String id, Object beanObj) {
        registerSingleton(id, beanObj);
    }

    @Override
    public Object getBean(String id) {
        Object bean = getSingleton(id);
        if (bean != null) {
            return bean;
        }

        BeanDefinition beanDefinition = beanDefinitionMap.get(id);
        if (beanDefinition == null) {
            throw new BeanException(String.format("未找到id为%s的Bean的定义", id));
        }

        try {
            bean = Class.forName(beanDefinition.getClassName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BeanException(String.format("id为%s的Bean初始化失败", id));
        }

        registerSingleton(id, bean);
        return bean;
    }
}

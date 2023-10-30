package com.whg.iSpring.ch01.v2;

import com.whg.iSpring.ch01.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public class SimpleBeanFactory implements BeanFactory{

    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    private final Map<String, Object> beanMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        String id = beanDefinition.getId();
        if(beanDefinitionMap.put(id, beanDefinition) != null){
            throw new BeanException(String.format("id为%s的Bean重复定义", id));
        }
    }

    @Override
    public Object getBean(String id) {
        Object bean = beanMap.get(id);
        if(bean != null){
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

        beanMap.put(id, bean);
        return bean;
    }
}

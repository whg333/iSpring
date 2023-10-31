package com.whg.iSpring.ch02.v2;

import com.whg.iSpring.ch02.BeanDefinition;

public interface BeanFactory {
    void registerBeanDefinition(BeanDefinition beanDefinition);
    Object getBean(String id);
}

package com.whg.iSpring.ch01.v2;

import com.whg.iSpring.ch01.BeanDefinition;

public interface BeanFactory {
    void registerBeanDefinition(BeanDefinition beanDefinition);
    Object getBean(String id);
}

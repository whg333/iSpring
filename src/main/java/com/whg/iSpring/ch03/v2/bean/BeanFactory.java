package com.whg.iSpring.ch03.v2.bean;

public interface BeanFactory {
    void registerBean(String id, Object beanObj);
    Object getBean(String id);
}

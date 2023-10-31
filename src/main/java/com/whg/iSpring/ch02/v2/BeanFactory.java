package com.whg.iSpring.ch02.v2;

public interface BeanFactory {
    void registerBean(String id, Object beanObj);
    Object getBean(String id);
}

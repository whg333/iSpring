package com.whg.iSpring.ch03.v2;

public interface SingletonBeanRegistry {
    void registerSingleton(String id, Object beanObj);
    Object getSingleton(String id);
}

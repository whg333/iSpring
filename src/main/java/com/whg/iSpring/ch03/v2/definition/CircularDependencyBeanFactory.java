package com.whg.iSpring.ch03.v2.definition;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;

public class CircularDependencyBeanFactory extends ReferenceBeanFactory {

    protected final ConcurrentHashMap<String, Object> earlySingletonMap = new ConcurrentHashMap<>();

    @Override
    public Object doGetBean(String id) {
        Object bean = super.doGetBean(id);
        if (bean != null) {
            return bean;
        }
        return earlySingletonMap.get(id);
    }

    @Override
    protected Object createBean(BeanDefinition beanDefinition) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Object bean = doCreateBean(beanDefinition);
        earlySingletonMap.put(beanDefinition.getId(), bean);
        handleProperties(bean, beanDefinition);
        return bean;
    }

}

package com.whg.iSpring.ch03.v2.definition;

import com.whg.iSpring.ch03.v2.bean.BeanException;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;

public class CircularDependencyBeanFactory extends ReferenceBeanFactory {

    protected final ConcurrentHashMap<String, Object> earlySingletonMap = new ConcurrentHashMap<>();

    @Override
    public Object getBean(String id) {
        Object bean = getSingleton(id);
        if (bean != null) {
            return bean;
        }

        Object earlyBean = earlySingletonMap.get(id);
        if(earlyBean != null){
            return earlyBean;
        }

        BeanDefinition beanDefinition = beanDefinitionMap.get(id);
        if (beanDefinition == null) {
            throw new BeanException(String.format("未找到id为%s的Bean的定义", id));
        }

        try {
            bean = createBean(beanDefinition);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BeanException(String.format("id为%s的Bean初始化失败", id));
        }

        registerSingleton(id, bean);
        return bean;
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

package com.whg.iSpring.ch03.v2;

import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final ConcurrentHashMap<String, Object> singletonMap = new ConcurrentHashMap<>();

    @Override
    public void registerSingleton(String id, Object beanObj) {
        if(singletonMap.put(id, beanObj) != null){
            throw new BeanException(String.format("id为%s的Bean重复", id));
        }
    }

    @Override
    public Object getSingleton(String id) {
        return singletonMap.get(id);
    }

    protected Object removeSingleton(String id){
        return singletonMap.remove(id);
    }

}

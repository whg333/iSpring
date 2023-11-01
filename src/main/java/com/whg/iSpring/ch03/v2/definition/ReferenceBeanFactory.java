package com.whg.iSpring.ch03.v2.definition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReferenceBeanFactory extends SimpleBeanFactory {

    @Override
    protected Object createBean(BeanDefinition beanDefinition) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object bean;

        Class<?> clazz = Class.forName(beanDefinition.getClassName());
        BeanConstructorArgs beanArgs = beanDefinition.getArgs();
        int beanArgCount = beanArgs.getArgCount();
        if(beanArgCount <= 0){
                bean = clazz.newInstance();
        }else{
            TypeValueArray typeValueArray = parseTypeValueArray(beanArgs.getList());
            Class<?>[] argsTypes = typeValueArray.getTypes();
            Constructor<?> constructor = clazz.getConstructor(argsTypes);
            Object[] initArgs = typeValueArray.getValues();
            bean = constructor.newInstance(initArgs);
        }

        BeanProperties properties = beanDefinition.getProperties();
        int propertyCount = properties.getPropertyCount();
        if(propertyCount > 0){
            for(int i=0;i<propertyCount;i++){
                BeanProperty property = properties.getProperty(i);
                String name = property.getName();
                String setMethodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);

                Class<?>[] argsTypes = new Class<?>[1];
                Object[] args = new Object[1];
                if(property.isRef()){
                    String refId = property.getRef();
                    Object refBean = getBean(refId);
                    argsTypes[0] = refBean.getClass();
                    args[0] = refBean;
                }else{
                    TypeValueObj typeValueObj = parseTypeValueObj(property);
                    argsTypes[0] = typeValueObj.getType();
                    args[0] = typeValueObj.getValue();
                }

                Method setMethod = clazz.getDeclaredMethod(setMethodName, argsTypes);
                setMethod.invoke(bean, args);
            }
        }

        return bean;
    }

}

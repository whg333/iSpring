package com.whg.iSpring.ch03.v2.definition;

import com.whg.iSpring.ch03.v2.bean.BeanException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

                Method setMethod = bean.getClass().getDeclaredMethod(setMethodName, argsTypes);
                setMethod.invoke(bean, args);
            }
        }

        return bean;
    }

    protected Object doCreateBean(BeanDefinition beanDefinition) throws ClassNotFoundException,
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

        return bean;
    }

}

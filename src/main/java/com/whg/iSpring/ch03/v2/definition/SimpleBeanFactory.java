package com.whg.iSpring.ch03.v2.definition;

import com.whg.iSpring.ch03.v2.BeanException;
import com.whg.iSpring.ch03.v2.BeanFactory;
import com.whg.iSpring.ch03.v2.DefaultSingletonBeanRegistry;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    private final ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        String id = beanDefinition.getId();
        if (beanDefinitionMap.put(id, beanDefinition) != null) {
            throw new BeanException(String.format("id为%s的Bean重复定义", id));
        }
    }

    @Override
    public void registerBean(String id, Object beanObj) {
        registerSingleton(id, beanObj);
    }

    @Override
    public Object getBean(String id) {
        Object bean = getSingleton(id);
        if (bean != null) {
            return bean;
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

    private Object createBean(BeanDefinition beanDefinition) throws ClassNotFoundException,
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

                TypeValueObj typeValueObj = parseTypeValueObj(property);
                Class<?>[] argsTypes = { typeValueObj.getType() };
                Object[] args = { typeValueObj.getValue() };

                Method setMethod = clazz.getDeclaredMethod(setMethodName, argsTypes);
                setMethod.invoke(bean, args);
            }
        }

        return bean;
    }

    private TypeValueArray parseTypeValueArray(List<TypeValueStr> list){
        int size = list.size();
        Class<?>[] types = new Class[size];
        Object[] values = new Object[size];
        for(int i=0;i<size;i++){
            TypeValueStr typeValue = list.get(i);
            TypeValueObj typeValueObj = parseTypeValueObj(typeValue);
            types[i] = typeValueObj.getType();
            values[i] = typeValueObj.getValue();
        }
        return new TypeValueArray(types, values);
    }

    private TypeValueObj parseTypeValueObj(TypeValueStr typeValueStr){
        return parseTypeValueObj(typeValueStr.getType(), typeValueStr.getValue());
    }

    private TypeValueObj parseTypeValueObj(String typeStr, String valueStr){
        Class<?> type;
        Object value;
        if("String".equals(typeStr)){
            type = String.class;
            value = valueStr;
        }else if("int".equals(typeStr)){
            type = int.class;
            value = Integer.valueOf(valueStr);
        }else if("double".equals(typeStr)){
            type = double.class;
            value = Double.valueOf(valueStr);
        }else{
            throw new BeanException("Unknown type="+typeStr);
        }
        return new TypeValueObj(type, value);
    }

}

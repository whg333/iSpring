package com.whg.iSpring.ch03.v2.definition;

public class ReferenceBeanFactory extends SimpleBeanFactory {

    @Override
    protected TypeValueObj handleTypeValue(BeanProperty property){
        if(property.isRef()){
            String refId = property.getRef();
            Object refBean = getBean(refId);
            Class<?> refBeanType = refBean.getClass();
            return new TypeValueObj(refBeanType, refBean);
        }else{
            return parseTypeValueObj(property);
        }
    }

}

package com.whg.iSpring.ch03.v2.definition;

import java.util.ArrayList;
import java.util.List;

public class BeanProperties {

    private final List<BeanProperty> list = new ArrayList<>();

    public void addProperty(BeanProperty property){
        list.add(property);
    }

    public BeanProperty getProperty(int index){
        return list.get(index);
    }

    public int getPropertyCount(){
        return list.size();
    }

    public <T extends TypeValueStr> List<T> getList() {
        return (List<T>) list;
    }

}

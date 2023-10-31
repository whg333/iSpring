package com.whg.iSpring.ch03.v2.definition;

import java.util.ArrayList;
import java.util.List;

public class BeanConstructorArgs {

    private final List<BeanConstructorArg> list = new ArrayList<>();

    public void addArg(BeanConstructorArg arg){
        list.add(arg);
    }

    public BeanConstructorArg getArg(int index){
        return list.get(index);
    }

    public int getArgCount(){
        return list.size();
    }

    public <T extends TypeValueStr> List<T> getList() {
        return (List<T>) list;
    }
}

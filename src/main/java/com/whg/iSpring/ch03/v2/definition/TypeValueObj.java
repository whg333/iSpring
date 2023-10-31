package com.whg.iSpring.ch03.v2.definition;

public class TypeValueObj {

    protected final Class<?> type;
    protected final Object value;

    public TypeValueObj(Class<?> type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Class<?> getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

}

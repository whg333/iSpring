package com.whg.iSpring.ch03.v2.definition;

public class TypeValueArray {

    private final Class<?>[] types;
    private final Object[] values;

    public TypeValueArray(Class<?>[] types, Object[] values) {
        this.types = types;
        this.values = values;
    }

    public Class<?>[] getTypes() {
        return types;
    }

    public Object[] getValues() {
        return values;
    }
}

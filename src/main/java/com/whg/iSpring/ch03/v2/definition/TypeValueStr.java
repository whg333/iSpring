package com.whg.iSpring.ch03.v2.definition;

public class TypeValueStr {

    protected final String type;
    protected final String value;

    public TypeValueStr(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

}

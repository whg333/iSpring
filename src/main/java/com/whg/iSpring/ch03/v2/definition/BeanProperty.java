package com.whg.iSpring.ch03.v2.definition;

public class BeanProperty extends TypeValueStr {

    private final String name;

    public BeanProperty(String type, String name, String value) {
        super(type, value);
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

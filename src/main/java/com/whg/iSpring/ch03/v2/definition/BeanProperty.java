package com.whg.iSpring.ch03.v2.definition;

public class BeanProperty extends TypeValueStr {

    private final String name;
    private final String ref;

    public BeanProperty(String type, String name, String value, String ref) {
        super(type, value);
        this.name = name;
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public boolean isRef(){
        return ref != null && ref.length() > 0;
    }

    public String getRef() {
        return ref;
    }
}

package com.whg.iSpring.ch03.v2.definition;

public class BeanDefinition {

    private String id;
    private String className;

    private BeanConstructorArgs args;
    private BeanProperties properties;

    public BeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public BeanConstructorArgs getArgs() {
        return args;
    }

    public void setArgs(BeanConstructorArgs args) {
        this.args = args;
    }

    public BeanProperties getProperties() {
        return properties;
    }

    public void setProperties(BeanProperties properties) {
        this.properties = properties;
    }
}

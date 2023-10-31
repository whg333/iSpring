package com.whg.iSpring.ch03;

public class AServiceImpl implements AService{

    private String name;
    private int level;

    private String property1;
    private double property2;

    public AServiceImpl(){

    }

    public AServiceImpl(String name, int level) {
        this.name = name;
        this.level = level;
        System.out.println("AServiceImpl constructor : "+this);
    }

    @Override
    public String toString() {
        return "AServiceImpl{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", property1='" + property1 + '\'' +
                ", property2=" + property2 +
                '}';
    }

    @Override
    public void sayHi() {
        System.out.println("Hi, ch03");
    }

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public double getProperty2() {
        return property2;
    }

    public void setProperty2(double property2) {
        this.property2 = property2;
    }
}

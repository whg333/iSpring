package com.whg.iSpring.ch03.v3;

public class ABean {

    private String name;
    private int level;

    private String property1;
    private double property2;

    private BBean bbean;

    public ABean(){

    }

    public ABean(String name, int level) {
        this.name = name;
        this.level = level;
        System.out.println("ABean constructor : "+this);
    }

    @Override
    public String toString() {
        return "ABean{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", property1='" + property1 + '\'' +
                ", property2=" + property2 +
                ", bbean=" + bbean +
                '}';
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

    public void setBbean(BBean bbean) {
        this.bbean = bbean;
    }
}

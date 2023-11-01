package com.whg.iSpring.ch03.v3;

public class CBean {

    private ABean abean;

    public String toStr() {
        return this+"["+hashCode()+"] -> CBean{" +
                "abean=" + abean +
                '}';
    }

    public void setAbean(ABean abean) {
        this.abean = abean;
    }
}

package com.whg.iSpring.ch03.v3;

public class BBean {

    private CBean cbean;

    public String toStr() {
        return this+"["+hashCode()+"] -> BBean{" +
                "cbean=" + cbean +
                '}';
    }

    public void setCbean(CBean cbean) {
        this.cbean = cbean;
    }
}

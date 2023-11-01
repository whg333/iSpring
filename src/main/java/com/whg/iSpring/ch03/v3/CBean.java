package com.whg.iSpring.ch03.v3;

public class CBean {

    private ABean abean;

    @Override
    public String toString() {
        return "CBean{" +
                "abean=" + abean +
                '}';
    }

    public void setAbean(ABean abean) {
        this.abean = abean;
    }
}

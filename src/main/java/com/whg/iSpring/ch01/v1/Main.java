package com.whg.iSpring.ch01.v1;

import com.whg.iSpring.ch01.AService;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("ch01/v1/beans.xml");
        AService aService = (AService) ctx.getBean("aservice");
        aService.sayHi();
    }

}

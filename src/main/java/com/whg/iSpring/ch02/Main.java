package com.whg.iSpring.ch02;

import com.whg.iSpring.ch02.v2.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("ch02/v2/beans.xml");
        AService aService = (AService) ctx.getBean("bservice");
        aService.sayHi();
    }

}

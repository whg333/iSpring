package com.whg.iSpring.ch03;

import com.whg.iSpring.ch03.v2.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("ch03/v2/beans.xml");
        AService aService = (AService) ctx.getBean("aservice");
        System.out.println("After getBean(\"aservice\") : " +aService);
        aService.sayHi();
    }

}

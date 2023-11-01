package com.whg.iSpring.ch03.v2;

import com.whg.iSpring.ch03.v2.bean.ClassPathXmlApplicationContext;
import com.whg.iSpring.ch03.v2.definition.SimpleBeanFactory;

public class Main {

    public static void main(String[] args) {
        SimpleBeanFactory beanFactory = new SimpleBeanFactory();
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(beanFactory, "ch03/v2/beans.xml");
        AService aService = (AService) ctx.getBean("aservice");
        System.out.println("After getBean(\"aservice\") : " +aService);
        aService.sayHi();
    }

}

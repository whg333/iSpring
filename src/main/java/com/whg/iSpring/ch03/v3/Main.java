package com.whg.iSpring.ch03.v3;

import com.whg.iSpring.ch03.v2.bean.ClassPathXmlApplicationContext;
import com.whg.iSpring.ch03.v2.definition.ReferenceBeanFactory;
import com.whg.iSpring.ch03.v2.definition.SimpleBeanFactory;

public class Main {

    public static void main(String[] args) {
        SimpleBeanFactory beanFactory = new ReferenceBeanFactory();
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(beanFactory, "ch03/v3/beans.xml");
        ABean a = (ABean) ctx.getBean("a");
        System.out.println("After getBean(\"a\") : " +a);
        BBean b = (BBean) ctx.getBean("b");
        System.out.println("b = "+b.toStr());
    }

}

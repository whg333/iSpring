package com.whg.iSpring.ch03.v4;

import com.whg.iSpring.ch03.v2.bean.ClassPathXmlApplicationContext;
import com.whg.iSpring.ch03.v2.definition.CircularDependencyBeanFactory;
import com.whg.iSpring.ch03.v2.definition.SimpleBeanFactory;
import com.whg.iSpring.ch03.v3.ABean;
import com.whg.iSpring.ch03.v3.BBean;
import com.whg.iSpring.ch03.v3.CBean;

public class Main {

    public static void main(String[] args) {
        SimpleBeanFactory beanFactory = new CircularDependencyBeanFactory();
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(beanFactory, "ch03/v4/beans.xml");
        ABean a = (ABean) ctx.getBean("a");
        System.out.println("After getBean(\"a\") : " +a);
        BBean b = (BBean) ctx.getBean("b");
        System.out.println("b = "+b.toStr());
        CBean c = (CBean) ctx.getBean("c");
        System.out.println("c = "+c.toStr());
    }

}

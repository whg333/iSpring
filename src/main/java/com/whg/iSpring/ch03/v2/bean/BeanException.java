package com.whg.iSpring.ch03.v2.bean;

public class BeanException extends RuntimeException{
    public BeanException(){
        super();
    }
    public BeanException(String message){
        super(message);
    }
}

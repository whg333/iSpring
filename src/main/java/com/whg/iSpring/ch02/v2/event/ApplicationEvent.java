package com.whg.iSpring.ch02.v2.event;

import java.util.EventObject;

public class ApplicationEvent extends EventObject {
    public ApplicationEvent(Object source) {
        super(source);
    }
}

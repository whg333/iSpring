package com.whg.iSpring.ch02.v2.event;

public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}

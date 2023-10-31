package com.whg.iSpring.ch03.v2.event;

public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}

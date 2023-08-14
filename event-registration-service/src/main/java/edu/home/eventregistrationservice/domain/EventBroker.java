package edu.home.eventregistrationservice.domain;

public interface EventBroker {

    void publishEvent(EventDTO eventDTO);

}

package edu.home.subscriptionservice.dto;

public class EventSubscriptionDTO {

    private String eventName;

    private String serviceName;

    public String getEventName() {
        return eventName;
    }

    public EventSubscriptionDTO setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public String getServiceName() {
        return serviceName;
    }

    public EventSubscriptionDTO setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }
}

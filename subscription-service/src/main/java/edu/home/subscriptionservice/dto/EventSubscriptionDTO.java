package edu.home.subscriptionservice.dto;

public class EventSubscriptionDTO {

    private String eventName;

    private boolean eventChecked;

    public String getEventName() {
        return eventName;
    }

    public EventSubscriptionDTO setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public boolean isEventChecked() {
        return eventChecked;
    }

    public EventSubscriptionDTO setEventChecked(boolean eventChecked) {
        this.eventChecked = eventChecked;
        return this;
    }
}

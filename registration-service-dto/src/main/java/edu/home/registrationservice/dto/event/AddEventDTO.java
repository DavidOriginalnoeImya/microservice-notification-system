package edu.home.registrationservice.dto.event;

public class AddEventDTO {
    private String eventName;

    private String eventCaption;

    private String eventDomainServiceName;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDomainServiceName() {
        return eventDomainServiceName;
    }

    public void setEventDomainServiceName(String eventDomainServiceName) {
        this.eventDomainServiceName = eventDomainServiceName;
    }

    public String getEventCaption() {
        return eventCaption;
    }

    public void setEventCaption(String eventCaption) {
        this.eventCaption = eventCaption;
    }
}

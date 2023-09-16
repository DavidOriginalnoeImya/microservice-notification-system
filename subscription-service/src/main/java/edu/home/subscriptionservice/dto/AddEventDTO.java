package edu.home.subscriptionservice.dto;

public class AddEventDTO extends AddEntityDTO {

    private String eventServiceName;

    public String getEventServiceName() {
        return eventServiceName;
    }

    public void setEventServiceName(String eventServiceName) {
        this.eventServiceName = eventServiceName;
    }

}

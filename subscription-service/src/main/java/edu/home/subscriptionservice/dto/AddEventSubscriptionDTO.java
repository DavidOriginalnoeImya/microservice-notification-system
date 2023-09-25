package edu.home.subscriptionservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AddEventSubscriptionDTO {

    @JsonIgnore
    private String userGuid;

    private String eventName;
    
    private String domainAppName;


    public String getUserGuid() {
        return userGuid;
    }

    public AddEventSubscriptionDTO setUserGuid(String userGuid) {
        this.userGuid = userGuid;
        return this;
    }

    public String getEventName() {
        return eventName;
    }

    public AddEventSubscriptionDTO setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public String getDomainAppName() {
        return domainAppName;
    }

    public AddEventSubscriptionDTO setDomainAppName(String domainAppName) {
        this.domainAppName = domainAppName;
        return this;
    }
}

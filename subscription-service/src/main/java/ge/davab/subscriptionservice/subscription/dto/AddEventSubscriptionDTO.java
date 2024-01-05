package ge.davab.subscriptionservice.subscription.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AddEventSubscriptionDTO {

    @JsonIgnore
    private String userGuid;

    private String eventName;
    
    private String serviceName;


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

    public String getServiceName() {
        return serviceName;
    }

    public AddEventSubscriptionDTO setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }
}

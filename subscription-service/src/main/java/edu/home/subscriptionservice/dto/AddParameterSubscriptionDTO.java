package edu.home.subscriptionservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.home.subscriptionservice.data.parameter.InputType;

public class AddParameterSubscriptionDTO {

    @JsonIgnore
    private String userGuid;
    
    private String parameterName;

    private String eventName;
    
    private String domainAppName;

    public String getParameterName() {
        return parameterName;
    }

    public AddParameterSubscriptionDTO setParameterName(String parameterName) {
        this.parameterName = parameterName;
        return this;
    }

    public String getEventName() {
        return eventName;
    }

    public AddParameterSubscriptionDTO setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public String getDomainAppName() {
        return domainAppName;
    }

    public AddParameterSubscriptionDTO setDomainAppName(String domainAppName) {
        this.domainAppName = domainAppName;
        return this;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public AddParameterSubscriptionDTO setUserGuid(String userGuid) {
        this.userGuid = userGuid;
        return this;
    }
}

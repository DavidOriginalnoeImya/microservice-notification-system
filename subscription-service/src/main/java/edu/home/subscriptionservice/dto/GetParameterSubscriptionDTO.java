package edu.home.subscriptionservice.dto;

public class GetParameterSubscriptionDTO {

    private String userGuid;

    private String parameterName;

    private String eventName;

    private String domainAppName;
    
    public String getUserGuid() {
        return userGuid;
    }

    public GetParameterSubscriptionDTO setUserGuid(String userGuid) {
        this.userGuid = userGuid;
        return this;
    }

    public String getParameterName() {
        return parameterName;
    }

    public GetParameterSubscriptionDTO setParameterName(String parameterName) {
        this.parameterName = parameterName;
        return this;
    }

    public String getEventName() {
        return eventName;
    }

    public GetParameterSubscriptionDTO setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public String getDomainAppName() {
        return domainAppName;
    }

    public GetParameterSubscriptionDTO setDomainAppName(String domainAppName) {
        this.domainAppName = domainAppName;
        return this;
    }
}

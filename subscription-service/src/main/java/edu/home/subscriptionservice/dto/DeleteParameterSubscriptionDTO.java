package edu.home.subscriptionservice.dto;

public class DeleteParameterSubscriptionDTO {

    private String userGuid;

    private String parameterName;

    private String eventName;

    private String domainAppName;
    
    public String getUserGuid() {
        return userGuid;
    }

    public DeleteParameterSubscriptionDTO setUserGuid(String userGuid) {
        this.userGuid = userGuid;
        return this;
    }

    public String getParameterName() {
        return parameterName;
    }

    public DeleteParameterSubscriptionDTO setParameterName(String parameterName) {
        this.parameterName = parameterName;
        return this;
    }

    public String getEventName() {
        return eventName;
    }

    public DeleteParameterSubscriptionDTO setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public String getDomainAppName() {
        return domainAppName;
    }

    public DeleteParameterSubscriptionDTO setDomainAppName(String domainAppName) {
        this.domainAppName = domainAppName;
        return this;
    }
}

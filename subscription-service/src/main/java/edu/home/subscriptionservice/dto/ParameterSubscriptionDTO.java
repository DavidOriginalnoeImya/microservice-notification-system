package edu.home.subscriptionservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ParameterSubscriptionDTO {

    private String parameterName;

    @JsonIgnore
    private String eventName;

    @JsonIgnore
    private String serviceName;

    public String getParameterName() {
        return parameterName;
    }

    public ParameterSubscriptionDTO setParameterName(String parameterName) {
        this.parameterName = parameterName;
        return this;
    }

    public String getEventName() {
        return eventName;
    }

    public ParameterSubscriptionDTO setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public String getServiceName() {
        return serviceName;
    }

    public ParameterSubscriptionDTO setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }
}

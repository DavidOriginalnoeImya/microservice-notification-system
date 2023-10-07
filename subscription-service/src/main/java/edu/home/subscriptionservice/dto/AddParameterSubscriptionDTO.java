package edu.home.subscriptionservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "inputType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AddMultiStringParamSubscriptionDTO.class, name = "MULTISELECT"),
        @JsonSubTypes.Type(
                value = AddSingleStringParamSubscriptionDTO.class, names = {"INPUT", "SELECT"}
        )
})
public abstract class AddParameterSubscriptionDTO {

    @JsonIgnore
    private String userGuid;

    private String parameterName;

    private String eventName;

    private String domainAppName;

    public String getUserGuid() {
        return userGuid;
    }

    public AddParameterSubscriptionDTO setUserGuid(String userGuid) {
        this.userGuid = userGuid;
        return this;
    }

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

    public AddParameterSubscriptionDTO setDomainNameApp(String domainAppName) {
        this.domainAppName = domainAppName;
        return this;
    }

}

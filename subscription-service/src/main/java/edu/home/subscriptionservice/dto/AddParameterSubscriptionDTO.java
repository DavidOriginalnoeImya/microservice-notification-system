package edu.home.subscriptionservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSubTypes({
        @JsonSubTypes.Type(AddMultiStringParamSubscriptionDTO.class)
})
public abstract class AddParameterSubscriptionDTO<ValueType> {

    @JsonIgnore
    private String userGuid;

    private String parameterName;

    private String eventName;

    private String domainAppName;

    public String getUserGuid() {
        return userGuid;
    }

    public AddParameterSubscriptionDTO<ValueType> setUserGuid(String userGuid) {
        this.userGuid = userGuid;
        return this;
    }

    public String getParameterName() {
        return parameterName;
    }

    public AddParameterSubscriptionDTO<ValueType> setParameterName(String parameterName) {
        this.parameterName = parameterName;
        return this;
    }

    public String getEventName() {
        return eventName;
    }

    public AddParameterSubscriptionDTO<ValueType> setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public String getDomainAppName() {
        return domainAppName;
    }

    public AddParameterSubscriptionDTO<ValueType> setDomainNameApp(String domainAppName) {
        this.domainAppName = domainAppName;
        return this;
    }

    public abstract ValueType getValue();

    public abstract AddParameterSubscriptionDTO<ValueType> setValue(ValueType value);

}

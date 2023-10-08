package edu.home.subscriptionservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "inputType")
@JsonSubTypes({
        @JsonSubTypes.Type(
                value = UpdateMultiStringParamSubscriptionDTO.class, name = "MULTISELECT"
        ),
        @JsonSubTypes.Type(
                value = UpdateSingleStringParamSubscriptionDTO.class, names = {"INPUT", "SELECT"}
        )
})
public abstract class UpdateParameterSubscriptionDTO {

    @JsonIgnore
    private String userGuid;

    private String parameterName;

    private String eventName;

    private String domainAppName;

    public String getUserGuid() {
        return userGuid;
    }

    public UpdateParameterSubscriptionDTO setUserGuid(String userGuid) {
        this.userGuid = userGuid;
        return this;
    }

    public String getParameterName() {
        return parameterName;
    }

    public UpdateParameterSubscriptionDTO setParameterName(String parameterName) {
        this.parameterName = parameterName;
        return this;
    }

    public String getEventName() {
        return eventName;
    }

    public UpdateParameterSubscriptionDTO setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public String getDomainAppName() {
        return domainAppName;
    }

    public UpdateParameterSubscriptionDTO setDomainNameApp(String domainAppName) {
        this.domainAppName = domainAppName;
        return this;
    }

}

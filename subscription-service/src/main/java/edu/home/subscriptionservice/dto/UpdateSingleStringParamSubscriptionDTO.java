package edu.home.subscriptionservice.dto;

public class UpdateSingleStringParamSubscriptionDTO
        extends UpdateParameterSubscriptionDTO {

    private String value;

    public String getValue() {
        return value;
    }

    public UpdateParameterSubscriptionDTO setValue(String value) {
        this.value = value;
        return this;
    }
}

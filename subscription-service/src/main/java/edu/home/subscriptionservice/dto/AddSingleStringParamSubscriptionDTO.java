package edu.home.subscriptionservice.dto;

public class AddSingleStringParamSubscriptionDTO
        extends AddParameterSubscriptionDTO {

    private String value;

    public String getValue() {
        return value;
    }

    public AddParameterSubscriptionDTO setValue(String value) {
        this.value = value;
        return this;
    }
}

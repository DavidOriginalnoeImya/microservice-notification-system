package edu.home.subscriptionservice.dto;

public class BoolParameterSubscriptionValueDTO
        implements ParameterSubscriptionValueDTO<Boolean> {

    private boolean value;

    @Override
    public void setValue(Boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    }
}

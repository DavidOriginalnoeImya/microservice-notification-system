package edu.home.subscriptionservice.dto;

import java.util.ArrayList;
import java.util.List;

public class SingleStringParameterSubscriptionDTO
        extends ParameterSubscriptionDTO {

    private String value;

    public String getValue() {
        return value;
    }

    public ParameterSubscriptionDTO setValue(String value) {
        this.value = value;
        return this;
    }
}

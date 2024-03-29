package edu.home.subscriptionservice.dto;

import java.util.ArrayList;
import java.util.List;

public class MultiStringParameterSubscriptionDTO
        extends ParameterSubscriptionDTO {

    private List<String> value = new ArrayList<>();

    public List<String> getValue() {
        return value;
    }

    public ParameterSubscriptionDTO setValues(List<String> value) {
        this.value = value;
        return this;
    }
}

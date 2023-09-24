package edu.home.subscriptionservice.dto;

import java.util.ArrayList;
import java.util.List;

public class MultiStringParameterSubscriptionDTO
        extends ParameterSubscriptionDTO {

    private List<String> values = new ArrayList<>();

    public List<String> getValues() {
        return values;
    }

    public ParameterSubscriptionDTO setValues(List<String> values) {
        this.values = values;
        return this;
    }
}

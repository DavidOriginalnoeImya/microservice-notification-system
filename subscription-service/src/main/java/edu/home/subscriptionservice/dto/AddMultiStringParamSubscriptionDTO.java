package edu.home.subscriptionservice.dto;

import java.util.ArrayList;
import java.util.List;

public class AddMultiStringParamSubscriptionDTO extends AddParameterSubscriptionDTO {

    private List<String> values = new ArrayList<>();

    public List<String> getValues() {
        return values;
    }

    public AddParameterSubscriptionDTO setValue(List<String> value) {
        values = value;
        return this;
    }
}

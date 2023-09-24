package edu.home.subscriptionservice.dto;

import java.util.ArrayList;
import java.util.List;

public class AddMultiStringParamSubscriptionDTO
        extends AddParameterSubscriptionDTO<List<String>> {

    private List<String> values = new ArrayList<>();

    @Override
    public List<String> getValue() {
        return values;
    }

    @Override
    public AddParameterSubscriptionDTO<List<String>> setValue(List<String> value) {
        values = value;
        return this;
    }
}

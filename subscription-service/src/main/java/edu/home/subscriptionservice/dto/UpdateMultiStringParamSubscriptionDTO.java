package edu.home.subscriptionservice.dto;

import edu.home.subscriptionservice.data.parameter.InputType;

import java.util.ArrayList;
import java.util.List;

public class UpdateMultiStringParamSubscriptionDTO extends UpdateParameterSubscriptionDTO {

    public final String inputType = InputType.MULTISELECT.name();

    private List<String> values = new ArrayList<>();

    public List<String> getValues() {
        return values;
    }

    public UpdateParameterSubscriptionDTO setValue(List<String> value) {
        values = value;
        return this;
    }
}

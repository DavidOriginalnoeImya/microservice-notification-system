package edu.home.subscriptionservice.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AddMultiStringParamSubscriptionDTO.class)
})
public interface ParameterSubscriptionValueDTO<ValueType> {

    void setValue(ValueType valueType);

    ValueType getValue();
}

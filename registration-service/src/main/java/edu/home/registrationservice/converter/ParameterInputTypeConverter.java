package edu.home.registrationservice.converter;


import edu.home.registrationservice.dto.parameter.AddParameterDTO;
import edu.home.rsmessage.AddParameterMessage;

import java.util.Map;

public class ParameterInputTypeConverter {

    private final static Map<AddParameterDTO.InputType, AddParameterMessage.InputType> inputTypeMap = Map.of(
            AddParameterDTO.InputType.INPUT, AddParameterMessage.InputType.INPUT,
            AddParameterDTO.InputType.SELECT, AddParameterMessage.InputType.SELECT,
            AddParameterDTO.InputType.MULTISELECT, AddParameterMessage.InputType.MULTISELECT,
            AddParameterDTO.InputType.CHECKBOX, AddParameterMessage.InputType.CHECKBOX
    );

    public static AddParameterMessage.InputType convert(AddParameterDTO.InputType inputType) {
        return inputTypeMap.get(inputType);
    }
}

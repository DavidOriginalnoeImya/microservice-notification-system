package edu.home.subscriptionservice.kafka.message.handle;

import edu.home.rsmessage.AddParameterMessage;
import edu.home.subscriptionservice.data.parameter.Parameter;
import edu.home.subscriptionservice.dto.AddEntityDTO;
import edu.home.subscriptionservice.dto.AddParameterDTO;

import java.util.Map;

public class ParameterMessageHandler implements MessageHandler<AddParameterMessage> {

    private static final Map<AddParameterMessage.InputType, Parameter.InputType> inputTypeMap = Map.of(
            AddParameterMessage.InputType.INPUT, Parameter.InputType.INPUT,
            AddParameterMessage.InputType.MULTISELECT, Parameter.InputType.MULTISELECT,
            AddParameterMessage.InputType.SELECT, Parameter.InputType.SELECT,
            AddParameterMessage.InputType.CHECKBOX, Parameter.InputType.CHECKBOX
    );

    @Override
    public AddEntityDTO convertAddMessage(AddParameterMessage addParameterMessage) {
        AddParameterDTO addParameterDTO = new AddParameterDTO();
        addParameterDTO.setEntityName(addParameterMessage.getName());
        addParameterDTO.setEntityCaption(addParameterMessage.getCaption());
        addParameterDTO.setParameterOptions(addParameterMessage.getOptions());
        addParameterDTO.setParameterInputType(
                inputTypeMap.get(addParameterMessage.getInputType())
        );
        addParameterDTO.setParameterEventName(addParameterMessage.getEventName());
        addParameterDTO.setParameterServiceName(addParameterMessage.getServiceName());

        return addParameterDTO;
    }
}

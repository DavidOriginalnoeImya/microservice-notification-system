package edu.home.subscriptionservice.kafka.message.handle;

import edu.home.rsmessage.AddParameterMessage;
import edu.home.subscriptionservice.data.parameter.InputType;
import edu.home.subscriptionservice.dto.AddEntityDTO;
import edu.home.subscriptionservice.dto.AddParameterDTO;

public class ParameterMessageHandler implements MessageHandler<AddParameterMessage> {

    @Override
    public AddEntityDTO convertAddMessage(AddParameterMessage addParameterMessage) {
        AddParameterDTO addParameterDTO = new AddParameterDTO();
        addParameterDTO.setEntityName(addParameterMessage.getName());
        addParameterDTO.setEntityCaption(addParameterMessage.getCaption());
        addParameterDTO.setParameterInputType(
                InputType.valueOf(
                        addParameterMessage.getInputType().name()
                )
        );
        addParameterDTO.setParameterEventName(addParameterMessage.getEventName());
        addParameterDTO.setParameterServiceName(addParameterMessage.getServiceName());

        return addParameterDTO;
    }
}

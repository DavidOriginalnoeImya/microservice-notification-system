package edu.home.subscriptionservice.kafka.message.handle;

import edu.home.rsmessage.AddEntityMessage;
import edu.home.rsmessage.AddEventMessage;
import edu.home.subscriptionservice.dto.AddEntityDTO;
import edu.home.subscriptionservice.dto.AddEventDTO;

public class EventMessageHandler implements MessageHandler<AddEventMessage> {

    @Override
    public AddEntityDTO convertAddMessage(AddEventMessage addEventMessage) {
        AddEventDTO addEventDTO = new AddEventDTO();
        addEventDTO.setEntityName(addEventMessage.getName());
        addEventDTO.setEntityCaption(addEventMessage.getCaption());
        addEventDTO.setEventServiceName(addEventMessage.getServiceName());

        return addEventDTO;
    }
}

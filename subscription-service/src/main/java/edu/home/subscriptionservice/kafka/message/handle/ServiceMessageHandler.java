package edu.home.subscriptionservice.kafka.message.handle;

import edu.home.rsmessage.AddEntityMessage;
import edu.home.subscriptionservice.dto.AddEntityDTO;
import edu.home.subscriptionservice.dto.AddServiceDTO;

public class ServiceMessageHandler implements MessageHandler {
    @Override
    public AddEntityDTO convertAddMessage(AddEntityMessage addEntityMessage) {
        AddServiceDTO addServiceDTO = new AddServiceDTO();
        addServiceDTO.setEntityName(addEntityMessage.getName());
        addServiceDTO.setEntityCaption(addEntityMessage.getCaption());

        return addServiceDTO;
    }
}

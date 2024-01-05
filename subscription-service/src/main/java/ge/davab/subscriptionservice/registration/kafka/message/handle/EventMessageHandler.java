package ge.davab.subscriptionservice.registration.kafka.message.handle;

import edu.home.rsmessage.AddEventMessage;
import ge.davab.subscriptionservice.registration.dto.AddEntityDTO;
import ge.davab.subscriptionservice.registration.dto.AddEventDTO;

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

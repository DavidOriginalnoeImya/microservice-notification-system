package ge.davab.subscriptionservice.registration.kafka.message.handle;

import edu.home.rsmessage.AddEntityMessage;
import ge.davab.subscriptionservice.registration.dto.AddEntityDTO;
import ge.davab.subscriptionservice.registration.dto.AddServiceDTO;

public class ServiceMessageHandler implements MessageHandler {
    @Override
    public AddEntityDTO convertAddMessage(AddEntityMessage addEntityMessage) {
        AddServiceDTO addServiceDTO = new AddServiceDTO();
        addServiceDTO.setEntityName(addEntityMessage.getName());
        addServiceDTO.setEntityCaption(addEntityMessage.getCaption());

        return addServiceDTO;
    }
}

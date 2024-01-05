package ge.davab.subscriptionservice.registration.kafka.message.handle;

import edu.home.rsmessage.AddEntityMessage;
import ge.davab.subscriptionservice.registration.dto.AddEntityDTO;

public interface MessageHandler<AddMessageType extends AddEntityMessage> {

    AddEntityDTO convertAddMessage(AddMessageType addEntityMessage);
}

package edu.home.subscriptionservice.kafka.message.handle;

import edu.home.rsmessage.AddEntityMessage;
import edu.home.subscriptionservice.dto.AddEntityDTO;

public interface MessageHandler<AddMessageType extends AddEntityMessage> {

    AddEntityDTO convertAddMessage(AddMessageType addEntityMessage);
}

package edu.home.subscriptionservice.kafka.message.handle;

import edu.home.rsmessage.AddEntityMessage;
import edu.home.subscriptionservice.dto.AddEntityDTO;

public class ParameterMessageHandler implements MessageHandler {


    @Override
    public AddEntityDTO convertAddMessage(AddEntityMessage addEntityMessage) {
        return null;
    }
}

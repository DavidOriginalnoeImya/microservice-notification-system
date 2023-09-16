package edu.home.subscriptionservice.kafka.message.handle;

import edu.home.rsmessage.AddEventMessage;
import edu.home.rsmessage.AddParameterMessage;
import edu.home.rsmessage.AddServiceMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageHandlerFactory {

    private static final Map<Class<?>, MessageHandler> handlers = Map.of(
            AddServiceMessage.class, new ServiceMessageHandler(),
            AddEventMessage.class, new EventMessageHandler(),
            AddParameterMessage.class, new ParameterMessageHandler()
    );

    public MessageHandler get(Class<?> messageClass) {
        return handlers.get(messageClass);
    }
}

package ge.davab.subscriptionservice.registration.kafka.message.handle;

import edu.home.rsmessage.AddEventMessage;
import edu.home.rsmessage.AddServiceMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MessageHandlerFactory {

    private static final Map<Class<?>, MessageHandler> handlers = Map.of(
            AddServiceMessage.class, new ServiceMessageHandler(),
            AddEventMessage.class, new EventMessageHandler()
    );

    public MessageHandler get(Class<?> messageClass) {
        return handlers.get(messageClass);
    }
}

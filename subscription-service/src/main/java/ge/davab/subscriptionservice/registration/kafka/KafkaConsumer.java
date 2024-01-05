package ge.davab.subscriptionservice.registration.kafka;

import edu.home.rsmessage.AddEntityMessage;
import ge.davab.subscriptionservice.registration.dto.AddEntityDTO;
import ge.davab.subscriptionservice.registration.dto.AddEventDTO;
import ge.davab.subscriptionservice.registration.dto.AddServiceDTO;
import ge.davab.subscriptionservice.registration.kafka.message.handle.MessageHandlerFactory;
import ge.davab.subscriptionservice.registration.service.DomainAppService;
import ge.davab.subscriptionservice.registration.service.EntityService;
import ge.davab.subscriptionservice.registration.service.EventService;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
class KafkaConsumer {

    private final Map<Class<?>, Class<? extends EntityService>> services = Map.of(
            AddServiceDTO.class, DomainAppService.class,
            AddEventDTO.class, EventService.class
    );

    private final ApplicationContext applicationContext;

    private final MessageHandlerFactory messageHandlerFactory;

    public KafkaConsumer(
            ApplicationContext applicationContext,
            MessageHandlerFactory messageHandlerFactory
    ) {
        this.applicationContext = applicationContext;
        this.messageHandlerFactory = messageHandlerFactory;
    }

    @KafkaListener(topics = "${spring.kafka.consumer.add-entity-topic}")
    public void consumeAddEntityMessage(AddEntityMessage addEntityMessage) {
        AddEntityDTO addEntityDTO = messageHandlerFactory
                .get(addEntityMessage.getClass())
                .convertAddMessage(addEntityMessage);

        applicationContext
                .getBean(services.get(addEntityDTO.getClass()))
                .add(addEntityDTO);
    }
}

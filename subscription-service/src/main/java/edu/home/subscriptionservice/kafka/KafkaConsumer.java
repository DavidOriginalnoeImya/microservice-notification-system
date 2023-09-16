package edu.home.subscriptionservice.kafka;

import edu.home.rsmessage.AddEntityMessage;
import edu.home.subscriptionservice.dto.AddEntityDTO;
import edu.home.subscriptionservice.dto.AddEventDTO;
import edu.home.subscriptionservice.dto.AddServiceDTO;
import edu.home.subscriptionservice.kafka.message.handle.MessageHandlerFactory;
import edu.home.subscriptionservice.service.DomainAppService;
import edu.home.subscriptionservice.service.EntityService;
import edu.home.subscriptionservice.service.EventService;
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

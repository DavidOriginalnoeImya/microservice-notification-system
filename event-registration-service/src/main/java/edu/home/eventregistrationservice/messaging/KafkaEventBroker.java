package edu.home.eventregistrationservice.messaging;

import edu.home.eventregistrationservice.domain.EventBroker;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventBroker implements EventBroker {

    private final Source source;

    public KafkaEventBroker(Source source) {
        this.source = source;
    }

    @Override
    public void publishEvent(EventDTO eventDTO) {
        source.output().send(
                MessageBuilder
                        .withPayload(eventDTO)
                        .build()
        );
    }
}

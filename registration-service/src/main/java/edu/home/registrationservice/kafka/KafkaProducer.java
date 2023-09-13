package edu.home.registrationservice.kafka;

import edu.home.rsmessage.AddEntityMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class KafkaProducer {

    @Value("${spring.kafka.producer.add-entity-topic}")
    private String addEntityTopicName;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendAddEntityMessage(AddEntityMessage addEntityMessageDTO) {
        kafkaTemplate.send(addEntityTopicName, addEntityMessageDTO);
    }

}

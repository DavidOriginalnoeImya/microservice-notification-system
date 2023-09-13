package edu.home.registrationservice.service.kafka;

import edu.home.registrationservice.dto.kafka.AddEntityMessageDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Value("${spring.kafka.producer.add-entity-topic}")
    private String addEntityTopicName;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendAddEntityMessage(AddEntityMessageDTO addEntityMessageDTO) {
        kafkaTemplate.send(addEntityTopicName, addEntityMessageDTO);
    }

}

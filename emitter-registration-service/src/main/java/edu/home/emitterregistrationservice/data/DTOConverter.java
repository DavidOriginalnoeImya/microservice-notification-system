package edu.home.emitterregistrationservice.data;

import edu.home.emitterregistrationdto.AddEmitterDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@Service
@ApplicationScope
public class DTOConverter {

    public Emitter convertToEmitter(AddEmitterDTO addEmitterDTO) {
        Emitter emitter = new Emitter();
        emitter.setCaption(addEmitterDTO.getEmitterCaption());
        emitter.setKafkaTopicName(addEmitterDTO.getEmitterKafkaTopicName());

        return emitter;
    }
}

package edu.home.emitterregistrationservice.data;

import edu.home.emitterregistrationdto.AddEmitterDTO;
import edu.home.emitterregistrationservice.emitter.Emitter;
import org.springframework.stereotype.Component;

@Component
class EmitterRepository implements Emitter {

    private final EmitterJpaRepository emitterJpaRepository;

    private final DTOConverter dtoConverter;

    public EmitterRepository(EmitterJpaRepository emitterJpaRepository, DTOConverter dtoConverter) {
        this.emitterJpaRepository = emitterJpaRepository;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public void add(AddEmitterDTO addEmitterDTO) {
        emitterJpaRepository.save(dtoConverter.convertToEmitter(addEmitterDTO));
    }
}

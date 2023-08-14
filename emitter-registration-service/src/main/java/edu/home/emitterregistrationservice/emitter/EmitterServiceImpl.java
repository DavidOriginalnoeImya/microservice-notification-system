package edu.home.emitterregistrationservice.emitter;

import edu.home.emitterregistrationdto.AddEmitterDTO;
import org.springframework.stereotype.Service;

@Service
class EmitterServiceImpl implements EmitterService {

    private final Emitter emitter;

    public EmitterServiceImpl(Emitter emitter) {
        this.emitter = emitter;
    }

    @Override
    public void addEmitter(AddEmitterDTO addEmitterDTO) {
        emitter.add(addEmitterDTO);
    }
}

package edu.home.emitterregistrationservice.web;

import edu.home.emitterregistrationdto.AddEmitterDTO;
import edu.home.emitterregistrationservice.emitter.EmitterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emitter")
public class EmitterRest {

    private final EmitterService emitterService;

    public EmitterRest(EmitterService emitterService) {
        this.emitterService = emitterService;
    }

    @PostMapping
    public void addEmitter(AddEmitterDTO addEmitterDTO) {
        emitterService.addEmitter(addEmitterDTO);
    }
}

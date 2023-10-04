package edu.home.registrationservice.controller;

import edu.home.registrationservice.dto.ErrorDTO;
import edu.home.registrationservice.dto.ParameterDTO;
import edu.home.registrationservice.dto.parameter.AddParameterDTO;
import edu.home.registrationservice.exception.EntityAlreadyExistsException;
import edu.home.registrationservice.exception.EntityDoesntExistException;
import edu.home.registrationservice.kafka.KafkaProducer;
import edu.home.registrationservice.service.ParameterService;
import edu.home.rsmessage.AddEntityMessage;
import edu.home.rsmessage.AddParameterMessage;
import edu.home.rsmessage.AddParameterMessage.InputType;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/parameters")
public class ParameterController {

    private final ParameterService parameterService;

    private final KafkaProducer kafkaProducer;

    public ParameterController(ParameterService parameterService, KafkaProducer kafkaProducer) {
        this.parameterService = parameterService;
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping
    public ResponseEntity<List<ParameterDTO>> getParameters() {
        return ResponseEntity.ok(parameterService.getParameters());
    }

    @GetMapping("/{parameter_name}")
    public ResponseEntity<?> getParameter(
            @PathVariable("parameter_name") String parameterName,
            @RequestParam("event_name") String eventName,
            @RequestParam("service_name") String serviceName
    ) {
        try {
            return ResponseEntity
                    .ok(
                        parameterService
                                .getParameter(parameterName, eventName, serviceName)
                    );
        }
        catch (EntityDoesntExistException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorDTO(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> addParameter(HttpServletRequest httpRequest, AddParameterDTO addParameterDTO) {
        try {
            ParameterDTO parameterDTO = parameterService.addParameter(addParameterDTO);
            kafkaProducer.sendAddEntityMessage(createAddParameterMessage(addParameterDTO));

            return ResponseEntity
                    .created(URI.create(httpRequest.getRequestURL() + "/" + parameterDTO.getName()))
                    .body(parameterDTO);
        }
        catch (EntityAlreadyExistsException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorDTO(e.getMessage()));
        }
    }

    private AddEntityMessage createAddParameterMessage(AddParameterDTO addParameterDTO) {
        return new AddParameterMessage.Builder()
                .setName(addParameterDTO.getParameterName())
                .setCaption(addParameterDTO.getParameterCaption())
                .setInputType(InputType.valueOf(addParameterDTO.getInputType().name()))
                .setEventName(addParameterDTO.getParameterEvent())
                .setServiceName(addParameterDTO.getParameterDomainService())
                .build();
    }
}

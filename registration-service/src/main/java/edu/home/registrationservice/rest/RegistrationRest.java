package edu.home.registrationservice.rest;

import edu.home.registrationservice.dto.ServiceRegistrationDTO;
import edu.home.registrationservice.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/service")
public class RegistrationRest {

    private final RegistrationService registrationService;

    public RegistrationRest(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ServiceRegistrationDTO registerService(ServiceRegistrationDTO serviceRegistrationDTO) {
        return registrationService.registerService(serviceRegistrationDTO);
    }
}

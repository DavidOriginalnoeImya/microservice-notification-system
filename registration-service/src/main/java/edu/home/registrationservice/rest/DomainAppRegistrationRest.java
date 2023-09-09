package edu.home.registrationservice.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.home.registrationservice.dto.DomainAppDTO;
import edu.home.registrationservice.dto.ErrorDTO;
import edu.home.registrationservice.dto.ServiceRegistrationDTO;
import edu.home.registrationservice.dto.service.AddServiceDTO;
import edu.home.registrationservice.exception.ServiceAlreadyExistsException;
import edu.home.registrationservice.service.DomainAppRegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/services")
public class DomainAppRegistrationRest {

    private final DomainAppRegistrationService domainAppRegistrationService;

    public DomainAppRegistrationRest(DomainAppRegistrationService domainAppRegistrationService) {
        this.domainAppRegistrationService = domainAppRegistrationService;
    }

    @PostMapping
    public ResponseEntity<?> addDomainApp(AddServiceDTO addServiceDTO) throws URISyntaxException {
        try {
            DomainAppDTO domainAppDTO = domainAppRegistrationService.addDomainApp(addServiceDTO);

            return ResponseEntity
                    .created(new URI("/api/services/" + domainAppDTO.getDomainAppName()))
                    .body(domainAppDTO);
        }
        catch (ServiceAlreadyExistsException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorDTO(e.getMessage()));
        }
    }


}

package edu.home.registrationservice.rest;

import edu.home.registrationservice.dto.DomainAppDTO;
import edu.home.registrationservice.dto.ErrorDTO;
import edu.home.registrationservice.dto.service.AddServiceDTO;
import edu.home.registrationservice.exception.EntityAlreadyExistsException;
import edu.home.registrationservice.exception.EntityDoesntExistException;
import edu.home.registrationservice.service.DomainAppService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/services")
public class DomainAppRest {

    private final DomainAppService domainAppService;

    public DomainAppRest(DomainAppService domainAppService) {
        this.domainAppService = domainAppService;
    }

    @GetMapping
    public ResponseEntity<List<DomainAppDTO>> getDomainApps() {
        return ResponseEntity
                .ok(domainAppService.getDomainApps());
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getDomainApp(@PathVariable String name) {
        try {
            return ResponseEntity
                    .ok(domainAppService.getDomainApp(name));
        }
        catch (EntityDoesntExistException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorDTO(e.getMessage()));
        }
    }



    @PostMapping
    public ResponseEntity<?> addDomainApp(HttpServletRequest httpRequest, AddServiceDTO addServiceDTO) {
        try {
            DomainAppDTO domainAppDTO = domainAppService.addDomainApp(addServiceDTO);

            return ResponseEntity
                    .created(URI.create(httpRequest.getRequestURL() + "/" + domainAppDTO.getDomainAppName()))
                    .body(domainAppDTO);
        }
        catch (EntityAlreadyExistsException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorDTO(e.getMessage()));
        }
    }

}

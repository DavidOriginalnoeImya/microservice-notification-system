package edu.home.registrationservice.rest;

import edu.home.registrationservice.dto.DomainAppDTO;
import edu.home.registrationservice.dto.ErrorDTO;
import edu.home.registrationservice.dto.service.AddServiceDTO;
import edu.home.registrationservice.exception.service.ServiceAlreadyExistsException;
import edu.home.registrationservice.exception.service.ServiceDoesntExistException;
import edu.home.registrationservice.service.DomainAppService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
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
        catch (ServiceDoesntExistException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorDTO(e.getMessage()));
        }
    }



    @PostMapping
    public ResponseEntity<?> addDomainApp(AddServiceDTO addServiceDTO) throws URISyntaxException {
        try {
            DomainAppDTO domainAppDTO = domainAppService.addDomainApp(addServiceDTO);

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

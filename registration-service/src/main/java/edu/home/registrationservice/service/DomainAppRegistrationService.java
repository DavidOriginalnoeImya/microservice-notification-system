package edu.home.registrationservice.service;

import edu.home.registrationservice.data.service.DomainApp;
import edu.home.registrationservice.data.service.DomainAppRepository;
import edu.home.registrationservice.dto.DTOConverter;
import edu.home.registrationservice.dto.DomainAppDTO;
import edu.home.registrationservice.dto.ServiceRegistrationDTO;
import edu.home.registrationservice.dto.service.AddServiceDTO;
import edu.home.registrationservice.exception.ServiceAlreadyExistsException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * TODO: изменить гениальное имя класса
 */

@Service
public class DomainAppRegistrationService {

    private final DomainAppRepository domainAppRepository;

    public DomainAppRegistrationService(DomainAppRepository domainAppRepository) {
        this.domainAppRepository = domainAppRepository;
    }

    @Transactional
    public DomainAppDTO addDomainApp(AddServiceDTO addServiceDTO) {
        String appName = addServiceDTO.getServiceName(),
                appAddress = addServiceDTO.getServiceAddress();

        if (!domainAppRepository.existsByNameAndAddress(appName, appAddress)) {
            DomainApp domainApp = domainAppRepository.save(DTOConverter.convertFromDTO(addServiceDTO));
            return DTOConverter.convertToDTO(domainApp);
        }
        else {
            throw new ServiceAlreadyExistsException();
        }
    }

}

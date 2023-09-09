package edu.home.registrationservice.service;

import edu.home.registrationservice.data.service.DomainApp;
import edu.home.registrationservice.data.service.DomainAppRepository;
import edu.home.registrationservice.dto.DTOConverter;
import edu.home.registrationservice.dto.DomainAppDTO;
import edu.home.registrationservice.dto.service.AddServiceDTO;
import edu.home.registrationservice.exception.service.ServiceAlreadyExistsException;
import edu.home.registrationservice.exception.service.ServiceDoesntExistException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * TODO: изменить гениальное имя класса
 */

@Service
public class DomainAppService {

    private final DomainAppRepository domainAppRepository;

    public DomainAppService(DomainAppRepository domainAppRepository) {
        this.domainAppRepository = domainAppRepository;
    }

    @Transactional
    public List<DomainAppDTO> getDomainApps() {
        return domainAppRepository.findAll()
                .stream()
                .map(DTOConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public DomainAppDTO getDomainApp(String name) {
        DomainApp domainApp = domainAppRepository
                .getByName(name)
                .orElseThrow(ServiceDoesntExistException::new);

        return DTOConverter.convertToDTO(domainApp);
    }

    @Transactional
    public DomainAppDTO addDomainApp(AddServiceDTO addServiceDTO) {
        if (!domainAppRepository.existsByName(addServiceDTO.getServiceName())) {
            DomainApp domainApp = domainAppRepository.save(DTOConverter.convertFromDTO(addServiceDTO));
            return DTOConverter.convertToDTO(domainApp);
        }
        else {
            throw new ServiceAlreadyExistsException();
        }
    }
}

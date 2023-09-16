package edu.home.subscriptionservice.service;

import edu.home.subscriptionservice.data.domainapp.DomainApp;
import edu.home.subscriptionservice.data.domainapp.DomainAppRepository;
import edu.home.subscriptionservice.dto.AddEntityDTO;
import edu.home.subscriptionservice.dto.AddServiceDTO;
import edu.home.subscriptionservice.dto.DTOConverter;
import edu.home.subscriptionservice.dto.ServiceDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class DomainAppService implements EntityService<AddServiceDTO> {

    private final DomainAppRepository domainAppRepository;

    public DomainAppService(DomainAppRepository domainAppRepository) {
        this.domainAppRepository = domainAppRepository;
    }

    public List<ServiceDTO> getDomainApps() {
        return domainAppRepository.findAll()
                .stream()
                .map(DTOConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void add(AddServiceDTO entity) {
        Logger logger = Logger.getLogger(DomainAppService.class.getName());
        logger.info("-----------------OK--------------------------");
    }
}

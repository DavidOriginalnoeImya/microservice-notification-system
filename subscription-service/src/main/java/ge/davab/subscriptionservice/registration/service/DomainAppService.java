package ge.davab.subscriptionservice.registration.service;

import ge.davab.subscriptionservice.registration.data.domainapp.DomainApp;
import ge.davab.subscriptionservice.registration.data.domainapp.DomainAppRepository;
import ge.davab.subscriptionservice.registration.dto.AddServiceDTO;
import ge.davab.subscriptionservice.registration.dto.DomainAppDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DomainAppService implements EntityService<AddServiceDTO> {

    private final DomainAppRepository domainAppRepository;

    public DomainAppService(DomainAppRepository domainAppRepository) {
        this.domainAppRepository = domainAppRepository;
    }

    public List<DomainAppDTO> getDomainApps() {
        return domainAppRepository.findAll()
                .stream()
                .map(DomainApp::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void add(AddServiceDTO addServiceDTO) {
        domainAppRepository.save(addServiceDTO.toEntity());
    }
}

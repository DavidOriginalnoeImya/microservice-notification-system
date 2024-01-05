package ge.davab.subscriptionservice.registration.service;

import ge.davab.subscriptionservice.registration.data.domainapp.DomainApp;
import ge.davab.subscriptionservice.registration.data.domainapp.DomainAppRepository;
import ge.davab.subscriptionservice.registration.data.event.Event;
import ge.davab.subscriptionservice.registration.data.event.EventRepository;
import ge.davab.subscriptionservice.registration.dto.AddEventDTO;
import org.springframework.stereotype.Service;

@Service
public class EventService implements EntityService<AddEventDTO> {

    private final EventRepository eventRepository;

    private final DomainAppRepository domainAppRepository;

    public EventService(
            EventRepository eventRepository,
            DomainAppRepository domainAppRepository
    ) {
        this.eventRepository = eventRepository;
        this.domainAppRepository = domainAppRepository;
    }

    @Override
    public void add(AddEventDTO addEventDTO) {
        DomainApp domainApp = domainAppRepository
                .findByName(addEventDTO.getEventServiceName())
                .orElseThrow();

        Event event = addEventDTO.toEntity();
        event.setDomainApp(domainApp);
        eventRepository.save(event);
    }
}

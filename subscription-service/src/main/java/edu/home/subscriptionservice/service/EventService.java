package edu.home.subscriptionservice.service;

import edu.home.subscriptionservice.data.domainapp.DomainApp;
import edu.home.subscriptionservice.data.domainapp.DomainAppRepository;
import edu.home.subscriptionservice.data.event.Event;
import edu.home.subscriptionservice.data.event.EventRepository;
import edu.home.subscriptionservice.dto.AddEventDTO;
import edu.home.subscriptionservice.dto.DTOConverter;
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

        Event event = DTOConverter.convertFromDTO(addEventDTO);
        event.setService(domainApp);
        eventRepository.save(event);
    }
}

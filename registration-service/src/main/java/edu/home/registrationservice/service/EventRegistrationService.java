package edu.home.registrationservice.service;

import edu.home.registrationservice.data.event.Event;
import edu.home.registrationservice.data.event.EventRepository;
import edu.home.registrationservice.data.service.DomainApp;
import edu.home.registrationservice.data.service.DomainAppRepository;
import edu.home.registrationservice.dto.event.AddEventDTO;
import org.springframework.stereotype.Service;

@Service
public class EventRegistrationService {

    private EventRepository eventRepository;

    private DomainAppRepository domainAppRepository;

    public EventRegistrationService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void addEvent(AddEventDTO addEventDTO) {
        DomainApp domainApp = domainAppRepository.getByName(addEventDTO.getEventDomainServiceName());

        Event event = new Event();
        event.setName(addEventDTO.getEventName());
        event.setDomainService(domainApp);
    }
}

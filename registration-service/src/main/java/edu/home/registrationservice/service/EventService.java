package edu.home.registrationservice.service;

import edu.home.registrationservice.data.event.Event;
import edu.home.registrationservice.data.event.EventRepository;
import edu.home.registrationservice.data.service.DomainApp;
import edu.home.registrationservice.data.service.DomainAppRepository;
import edu.home.registrationservice.dto.DTOConverter;
import edu.home.registrationservice.dto.EventDTO;
import edu.home.registrationservice.dto.event.AddEventDTO;
import edu.home.registrationservice.exception.EntityDoesntExistException;
import edu.home.registrationservice.exception.EntityAlreadyExistsException;
import edu.home.registrationservice.exception.service.ServiceDoesntExistException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;

    private final DomainAppRepository domainAppRepository;

    public EventService(EventRepository eventRepository, DomainAppRepository domainAppRepository) {
        this.eventRepository = eventRepository;
        this.domainAppRepository = domainAppRepository;
    }

    @Transactional
    public EventDTO addEvent(AddEventDTO addEventDTO) {
        DomainApp domainApp = domainAppRepository
                .getByName(addEventDTO.getEventDomainServiceName())
                .orElseThrow(ServiceDoesntExistException::new);

        if (!eventRepository.existsByName(addEventDTO.getEventName())) {
            Event event = DTOConverter.convertFromDTO(addEventDTO);
            event.setDomainApp(domainApp);
            return DTOConverter.convertToDTO(eventRepository.save(event));
        }
        else {
            throw new EntityAlreadyExistsException("Event with same name already exists");
        }


    }

    public List<EventDTO> getEvents() {
        return eventRepository.findAll()
                .stream().map(DTOConverter::convertToDTO)
                .collect(Collectors.toList());
    }


    public EventDTO getEvent(String eventName, String domainAppName) {
            Event event = eventRepository
                    .getByNameAndDomainAppName(eventName, domainAppName)
                    .orElseThrow(() -> new EntityDoesntExistException(
                                    "Event with this name doesn't exist"
                            )
                    );

            return DTOConverter.convertToDTO(event);
    }
};

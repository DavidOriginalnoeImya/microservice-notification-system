package edu.home.subscriptionservice.service;

import edu.home.notificationsystem.exception.EntityDoesntExistException;
import edu.home.subscriptionservice.data.domainapp.DomainApp;
import edu.home.subscriptionservice.data.event.Event;
import edu.home.subscriptionservice.data.event.EventRepository;
import edu.home.subscriptionservice.data.subscription.EventSubscription;
import edu.home.subscriptionservice.data.subscription.EventSubscriptionRepository;
import edu.home.subscriptionservice.data.user.User;
import edu.home.subscriptionservice.data.user.UserRepository;
import edu.home.subscriptionservice.dto.AddEventSubscriptionDTO;
import edu.home.subscriptionservice.dto.DTOConverter;
import edu.home.subscriptionservice.dto.EventSubscriptionDTO;
import edu.home.subscriptionservice.dto.ParameterSubscriptionDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventSubscriptionService {

    private final EventSubscriptionRepository eventSubscriptionRepository;

    private final ParameterSubscriptionService parameterSubscriptionService;

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    public EventSubscriptionService(
            EventSubscriptionRepository eventSubscriptionRepository,
            ParameterSubscriptionService parameterSubscriptionService ,
            EventRepository eventRepository,
            UserRepository userRepository
    ) {
        this.eventSubscriptionRepository = eventSubscriptionRepository;
        this.parameterSubscriptionService = parameterSubscriptionService;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public EventSubscriptionDTO getEventSubscription(
            String userGuid, String eventName,
            String domainAppName
    ) {
        Event event = getEvent(eventName, domainAppName);
        return DTOConverter.convertToDTO(getEventSubscription(userGuid, event));
    }

    @Transactional
    public EventSubscriptionDTO addEventSubscription(
            AddEventSubscriptionDTO addEventSubscriptionDTO
    ) {
        User user = getUser(addEventSubscriptionDTO.getUserGuid());

        Event event = getEvent(
                addEventSubscriptionDTO.getEventName(),
                addEventSubscriptionDTO.getServiceName()
        );

        EventSubscription eventSubscription = new EventSubscription(event, user);

        return DTOConverter.convertToDTO(
                eventSubscriptionRepository.save(eventSubscription)
        );
    }

    @Transactional
    public EventSubscriptionDTO deleteEventSubscription(
            String userGuid, String eventName, String domainAppName
    ) {
        Event event = getEvent(eventName, domainAppName);

        EventSubscription eventSubscription = getEventSubscription(userGuid, event);
        eventSubscriptionRepository.delete(eventSubscription);

        return DTOConverter.convertToDTO(eventSubscription);
    }

    @Transactional
    public List<EventSubscriptionDTO> getEventSubscriptions(String userGuid, String domainAppName) {
        return eventSubscriptionRepository
                .getByUserGuidAndDomainAppName(userGuid, domainAppName)
                .stream().map(DTOConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    private EventSubscription getEventSubscription(String userGuid, Event event) {
        return eventSubscriptionRepository
                .getByEventAndUserGuid(event, userGuid)
                .orElseThrow(() -> new EntityDoesntExistException("Event subscription doesn't exist"));
    }

    private Event getEvent(String eventName, String domainAppName) {
        return eventRepository
                .getByNameAndDomainAppName(eventName, domainAppName)
                .orElseThrow(() -> new EntityDoesntExistException("Event doesn't exist"));
    }

    private User getUser(String userGuid) {
        return userRepository
                .getByGuid(userGuid)
                .orElseThrow(() -> new EntityDoesntExistException("User doesn't exist"));
    }
}

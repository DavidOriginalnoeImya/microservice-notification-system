package edu.home.subscriptionservice.service;

import edu.home.subscriptionservice.data.event.Event;
import edu.home.subscriptionservice.data.event.EventRepository;
import edu.home.subscriptionservice.data.subscription.EventSubscription;
import edu.home.subscriptionservice.data.subscription.EventSubscriptionRepository;
import edu.home.subscriptionservice.data.user.User;
import edu.home.subscriptionservice.data.user.UserRepository;
import edu.home.subscriptionservice.dto.AddEventSubscriptionDTO;
import edu.home.subscriptionservice.dto.DTOConverter;
import edu.home.subscriptionservice.dto.EventSubscriptionDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class EventSubscriptionService {

    private final EventSubscriptionRepository eventSubscriptionRepository;

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    public EventSubscriptionService(
            EventSubscriptionRepository eventSubscriptionRepository,
            EventRepository eventRepository,
            UserRepository userRepository
    ) {
        this.eventSubscriptionRepository = eventSubscriptionRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public EventSubscriptionDTO getEventSubscription(
            String userGuid, String eventName,
            String domainAppName
    ) {
        Event event = getEvent(eventName, domainAppName);

        return eventSubscriptionRepository
                .getByEventAndUserGuid(event, userGuid)
                .map(DTOConverter::convertToDTO)
                .orElse(
                        new EventSubscriptionDTO()
                                .setEventName(eventName)
                );
    }

    @Transactional
    public EventSubscriptionDTO addEventSubscription(
            AddEventSubscriptionDTO addEventSubscriptionDTO
    ) {
        User user = userRepository
                .getByGuid(addEventSubscriptionDTO.getUserGuid())
                .orElseThrow();

        Event event = getEvent(
                addEventSubscriptionDTO.getEventName(),
                addEventSubscriptionDTO.getDomainAppName()
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

        EventSubscription eventSubscription = eventSubscriptionRepository
                .getByEventAndUserGuid(event, userGuid)
                .orElseThrow();
        eventSubscriptionRepository.delete(eventSubscription);

        return DTOConverter.convertToDTO(eventSubscription);
    }

    private Event getEvent(String eventName, String domainAppName) {
        return eventRepository
                .getByNameAndDomainAppName(eventName, domainAppName)
                .orElseThrow();
    }
}

package edu.home.subscriptionservice.service;

import edu.home.subscriptionservice.data.event.Event;
import edu.home.subscriptionservice.data.event.EventRepository;
import edu.home.subscriptionservice.data.subscription.EventSubscriptionRepository;
import edu.home.subscriptionservice.dto.DTOConverter;
import edu.home.subscriptionservice.dto.EventSubscriptionDTO;
import org.springframework.stereotype.Service;

@Service
public class EventSubscriptionService {

    private final EventSubscriptionRepository eventSubscriptionRepository;

    private final EventRepository eventRepository;

    public EventSubscriptionService(
            EventSubscriptionRepository eventSubscriptionRepository,
            EventRepository eventRepository
    ) {
        this.eventSubscriptionRepository = eventSubscriptionRepository;
        this.eventRepository = eventRepository;
    }

    public EventSubscriptionDTO getEventSubscription(
            String userGuid, String eventName,
            String domainAppName
    ) {
        Event event = eventRepository
                .getByNameAndDomainAppName(eventName, domainAppName)
                .orElseThrow();

        return eventSubscriptionRepository
                .getByEventAndUserGuid(event, userGuid)
                .map(DTOConverter::convertToDTO)
                .orElse(
                        new EventSubscriptionDTO()
                                .setEventName(eventName)
                );
    }
}

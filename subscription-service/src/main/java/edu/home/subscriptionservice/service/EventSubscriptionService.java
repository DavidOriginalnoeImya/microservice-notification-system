package edu.home.subscriptionservice.service;

import edu.home.subscriptionservice.data.event.Event;
import edu.home.subscriptionservice.data.event.EventRepository;
import edu.home.subscriptionservice.data.parameter.Parameter;
import edu.home.subscriptionservice.data.subscription.EventSubscription;
import edu.home.subscriptionservice.data.subscription.EventSubscriptionRepository;
import edu.home.subscriptionservice.data.subscription.parameter.IParameterSubscriptionRepository;
import edu.home.subscriptionservice.data.subscription.parameter.ParameterSubscription;
import edu.home.subscriptionservice.data.user.User;
import edu.home.subscriptionservice.data.user.UserRepository;
import edu.home.subscriptionservice.dto.AddEventSubscriptionDTO;
import edu.home.subscriptionservice.dto.DTOConverter;
import edu.home.subscriptionservice.dto.EventSubscriptionDTO;
import edu.home.subscriptionservice.dto.ParameterSubscriptionDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
        User user = getUser(addEventSubscriptionDTO.getUserGuid());

        Event event = getEvent(
                addEventSubscriptionDTO.getEventName(),
                addEventSubscriptionDTO.getDomainAppName()
        );

        EventSubscription eventSubscription = new EventSubscription(event, user);

        List<ParameterSubscriptionDTO> parameterSubscriptionsDTO = parameterSubscriptionService
                        .addParameterSubscriptions(user, event.getParameters());

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

    private User getUser(String userGuid) {
        return userRepository
                .getByGuid(userGuid)
                .orElseThrow();
    }
}

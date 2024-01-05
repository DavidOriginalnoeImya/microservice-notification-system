package ge.davab.subscriptionservice.subscription.service;

import edu.home.notificationsystem.exception.EntityDoesntExistException;
import ge.davab.subscriptionservice.registration.data.event.Event;
import ge.davab.subscriptionservice.registration.data.event.EventRepository;
import ge.davab.subscriptionservice.subscription.data.event.EventSubscription;
import ge.davab.subscriptionservice.subscription.data.event.EventSubscriptionRepository;
import ge.davab.subscriptionservice.subscription.data.user.User;
import ge.davab.subscriptionservice.subscription.data.user.UserRepository;
import ge.davab.subscriptionservice.subscription.dto.AddEventSubscriptionDTO;
import ge.davab.subscriptionservice.subscription.dto.EventSubscriptionDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
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

    public EventSubscriptionDTO getEventSubscription(
            String userGuid, String eventName,
            String domainAppName
    ) {
        Event event = getEvent(eventName, domainAppName);
        return getEventSubscription(userGuid, event).toDTO();
    }

    public EventSubscriptionDTO addEventSubscription(AddEventSubscriptionDTO addEventSubscriptionDTO) {
        User user = getUser(addEventSubscriptionDTO.getUserGuid());

        Event event = getEvent(
                addEventSubscriptionDTO.getEventName(),
                addEventSubscriptionDTO.getServiceName()
        );

        EventSubscription eventSubscription = new EventSubscription(event, user);
        return eventSubscriptionRepository
                .save(eventSubscription)
                .toDTO();
    }

    public EventSubscriptionDTO deleteEventSubscription(
            String userGuid, String eventName, String domainAppName
    ) {
        Event event = getEvent(eventName, domainAppName);

        EventSubscription eventSubscription = getEventSubscription(userGuid, event);
        eventSubscriptionRepository.delete(eventSubscription);

        return eventSubscription.toDTO();
    }

    public List<EventSubscriptionDTO> getEventSubscriptions(String userGuid, String domainAppName) {
        return eventSubscriptionRepository
                .getByUserGuidAndDomainAppName(userGuid, domainAppName)
                .stream().map(EventSubscription::toDTO)
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

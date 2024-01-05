package ge.davab.subscriptionservice.subscription.service;

import edu.home.notificationsystem.exception.EntityDoesntExistException;
import ge.davab.subscriptionservice.registration.data.event.Event;
import ge.davab.subscriptionservice.registration.data.event.EventRepository;
import ge.davab.subscriptionservice.registration.service.EventService;
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

    private final EventService eventService;

    private final UserService userService;

    public EventSubscriptionService(
            EventSubscriptionRepository eventSubscriptionRepository,
            EventService eventService,
            UserService userService
    ) {
        this.eventSubscriptionRepository = eventSubscriptionRepository;
        this.eventService = eventService;
        this.userService = userService;
    }

    public EventSubscriptionDTO getEventSubscription(
            String userGuid, String eventName,
            String domainAppName
    ) {
        Event event = eventService.getEvent(eventName, domainAppName);
        return getEventSubscription(userGuid, event).toDTO();
    }

    public EventSubscriptionDTO addEventSubscription(AddEventSubscriptionDTO addEventSubscriptionDTO) {
        User user = userService.getUser(addEventSubscriptionDTO.getUserGuid());

        Event event = eventService.getEvent(
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
        Event event = eventService.getEvent(eventName, domainAppName);

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
}

package ge.davab.subscriptionservice.subscription.controller;

import edu.home.notificationsystem.exception.EntityDoesntExistException;
import ge.davab.subscriptionservice.subscription.controller.util.UriBuilder;
import ge.davab.subscriptionservice.subscription.dto.AddEventSubscriptionDTO;
import ge.davab.subscriptionservice.subscription.dto.EventSubscriptionDTO;
import ge.davab.subscriptionservice.subscription.service.EventSubscriptionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event-subs")
public class EventSubscriptionController {

    private final EventSubscriptionService eventSubscriptionService;

    private final String userGuid = "1234";

    private static final String EVENT_NAME_REQ_PARAM = "event-name";

    private static final String SERVICE_NAME_REQ_PARAM = "service-name";

    public EventSubscriptionController(
            EventSubscriptionService eventSubscriptionService
    ) {
        this.eventSubscriptionService = eventSubscriptionService;
    }

    @GetMapping("/{event-name}")
    public ResponseEntity<EventSubscriptionDTO> getEventSubscription(
            @PathVariable(EVENT_NAME_REQ_PARAM) String eventName,
            @RequestParam(SERVICE_NAME_REQ_PARAM) String domainAppName
    ) {
        return ResponseEntity.ok(eventSubscriptionService
                .getEventSubscription(userGuid, eventName, domainAppName));
    }

    @GetMapping
    public ResponseEntity<List<EventSubscriptionDTO>> getEventSubscriptions(
            @RequestParam(SERVICE_NAME_REQ_PARAM) String domainAppName
    ) {
        List<EventSubscriptionDTO> eventSubscriptionsDTO = eventSubscriptionService
                .getEventSubscriptions(userGuid, domainAppName);

        return ResponseEntity
                .ok(eventSubscriptionsDTO);
    }

    @PostMapping
    public ResponseEntity<EventSubscriptionDTO> addEventSubscription(
            HttpServletRequest httpRequest,
            @RequestBody AddEventSubscriptionDTO addEventSubscriptionDTO
    ) {
        EventSubscriptionDTO eventSubscriptionDTO = eventSubscriptionService
                .addEventSubscription(addEventSubscriptionDTO.setUserGuid(userGuid));

        return ResponseEntity
                .created(
                        new UriBuilder(httpRequest)
                                .setPathVariable(
                                        EVENT_NAME_REQ_PARAM,
                                        addEventSubscriptionDTO.getEventName()
                                )
                                .setPathVariable(
                                        SERVICE_NAME_REQ_PARAM,
                                        addEventSubscriptionDTO.getServiceName()
                                )
                                .build()
                )
                .body(eventSubscriptionDTO);
    }

    @DeleteMapping
    public ResponseEntity<EventSubscriptionDTO> deleteEventSubscription(
            @RequestParam("event-name") String eventName,
            @RequestParam("service-name") String domainAppName
    ) {
        EventSubscriptionDTO eventSubscriptionDTO = eventSubscriptionService
                .deleteEventSubscription(userGuid, eventName, domainAppName);

        return ResponseEntity
                .ok(eventSubscriptionDTO);
    }

    @ExceptionHandler(EntityDoesntExistException.class)
    public ResponseEntity<String> handleEntityDoesntExistException(EntityDoesntExistException e) {
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }
}

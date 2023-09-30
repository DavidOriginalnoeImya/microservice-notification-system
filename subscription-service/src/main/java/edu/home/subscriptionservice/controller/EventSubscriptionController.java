package edu.home.subscriptionservice.controller;

import edu.home.notificationsystem.exception.EntityDoesntExistException;
import edu.home.subscriptionservice.controller.util.UriBuilder;
import edu.home.subscriptionservice.dto.AddEventSubscriptionDTO;
import edu.home.subscriptionservice.dto.EventSubscriptionDTO;
import edu.home.subscriptionservice.service.EventSubscriptionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

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

    @GetMapping
    public ResponseEntity<?> getEventSubscription(
            @RequestParam(EVENT_NAME_REQ_PARAM) String eventName,
            @RequestParam(SERVICE_NAME_REQ_PARAM) String domainAppName
    ) {
        try {
            return ResponseEntity.ok(eventSubscriptionService
                    .getEventSubscription(userGuid, eventName, domainAppName));
        }
        catch (EntityDoesntExistException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> addEventSubscription(
            HttpServletRequest httpRequest,
            @RequestBody AddEventSubscriptionDTO addEventSubscriptionDTO
    ) {
        try {
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
        catch (EntityDoesntExistException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteEventSubscription(
            @RequestParam("event-name") String eventName,
            @RequestParam("service-name") String domainAppName
    ) {
        try {
            EventSubscriptionDTO eventSubscriptionDTO = eventSubscriptionService
                    .deleteEventSubscription(userGuid, eventName, domainAppName);

            return ResponseEntity
                    .ok(eventSubscriptionDTO);
        }
        catch (EntityDoesntExistException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}

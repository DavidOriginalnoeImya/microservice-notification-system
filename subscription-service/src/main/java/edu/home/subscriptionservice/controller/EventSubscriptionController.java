package edu.home.subscriptionservice.controller;

import edu.home.subscriptionservice.service.EventSubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/event-subs")
public class EventSubscriptionController {

    private EventSubscriptionService eventSubscriptionService;

    private final String userGuid = "1234";

    public EventSubscriptionController(
            EventSubscriptionService eventSubscriptionService
    ) {
        this.eventSubscriptionService = eventSubscriptionService;
    }

    @GetMapping
    public ResponseEntity<?> getEventSubscription(
            @RequestParam("event") String eventName,
            @RequestParam("service") String domainAppName
    ) {
        try {
            return ResponseEntity.ok(eventSubscriptionService
                    .getEventSubscription(userGuid, eventName, domainAppName));
        }
        catch (NoSuchElementException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

}

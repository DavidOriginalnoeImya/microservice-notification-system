package ge.davab.subscriptionservice.subscription.controller;

import ge.davab.subscriptionservice.subscription.data.user.UserRepository;
import ge.davab.subscriptionservice.subscription.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subscribers")
public class SubscriberController {

    private final UserService userService;

    public SubscriberController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getUsersGuidByEventSubscription(
        @RequestParam("event-name") String eventName,
        @RequestParam("service-name") String serviceName
    ) {
        return ResponseEntity.ok(
                userService.getUsersGuidByEventSubscription(eventName, serviceName)
        );
    }
}

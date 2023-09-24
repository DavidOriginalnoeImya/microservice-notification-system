package edu.home.subscriptionservice.controller;

import edu.home.subscriptionservice.dto.GetParameterSubscriptionDTO;
import edu.home.subscriptionservice.dto.ParameterSubscriptionDTO;
import edu.home.subscriptionservice.service.ParameterSubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/parameter-subs")
public class ParameterSubscriptionController {

    private final ParameterSubscriptionService parameterSubscriptionService;

    private final String userGuid = "1234";

    public ParameterSubscriptionController(
            ParameterSubscriptionService parameterSubscriptionService
    ) {
        this.parameterSubscriptionService = parameterSubscriptionService;
    }

    @GetMapping("/{parameter-name}")
    public ResponseEntity<?> getParameterSubscription(
            @PathVariable("parameter-name") String parameterName,
            @RequestParam("event-name") String eventName,
            @RequestParam("service-name") String domainAppName
    ) {
        try {
            ParameterSubscriptionDTO parameterSubscriptionDTO =
                    parameterSubscriptionService.getParameterSubscription(
                            new GetParameterSubscriptionDTO()
                                    .setUserGuid(userGuid)
                                    .setParameterName(parameterName)
                                    .setEventName(eventName)
                                    .setDomainAppName(domainAppName)
                    );

            return ResponseEntity
                    .ok(parameterSubscriptionDTO);
        }
        catch (NoSuchElementException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

//    @PostMapping(consumes = "application/json")
//    public ResponseEntity<?> addParameterSubscription(
//            @RequestBody AddParameterSubscriptionDTO<?> addParameterSubscriptionDTO
//    ) {
//        ParameterSubscriptionDTO parameterSubscriptionDTO = parameterSubscriptionService
//                .addParameterSubscription(
//                        addParameterSubscriptionDTO.setUserGuid(userGuid)
//                );
//
//        return ResponseEntity
//                .ok(parameterSubscriptionDTO);
//    }
}

package edu.home.subscriptionservice.controller;

import edu.home.notificationsystem.exception.EntityDoesntExistException;
import edu.home.subscriptionservice.controller.util.UriBuilder;
import edu.home.subscriptionservice.dto.AddParameterSubscriptionDTO;
import edu.home.subscriptionservice.dto.GetParameterSubscriptionDTO;
import edu.home.subscriptionservice.dto.ParameterSubscriptionDTO;
import edu.home.subscriptionservice.service.ParameterSubscriptionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/parameter-subs")
public class ParameterSubscriptionController {

    private final static int PARAM_SUB_DOESNT_EXIST = 225;

    private static final String EVENT_NAME_REQ_PARAM = "event-name";

    private static final String SERVICE_NAME_REQ_PARAM = "service-name";

    private final ParameterSubscriptionService parameterSubscriptionService;

    private final String userGuid = "1234";

    public ParameterSubscriptionController(
            ParameterSubscriptionService parameterSubscriptionService
    ) {
        this.parameterSubscriptionService = parameterSubscriptionService;
    }

    @GetMapping
    public ResponseEntity<?> getParameterSubscriptions(
            @RequestParam("event-name") String eventName,
            @RequestParam("service-name") String domainAppName
    ) {
        try {
            List<ParameterSubscriptionDTO> parameterSubscriptionsDTO = parameterSubscriptionService
                    .getParameterSubscriptions(userGuid, eventName, domainAppName);

            return ResponseEntity
                    .ok(parameterSubscriptionsDTO);
        }
        catch (EntityDoesntExistException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
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
        catch (EntityDoesntExistException e) {
            return ResponseEntity
                    .status(PARAM_SUB_DOESNT_EXIST)
                    .body("Current user isn't subscribe to this parameter");
        }
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> addParameterSubscription(
            HttpServletRequest httpRequest,
            @RequestBody AddParameterSubscriptionDTO addParameterSubscriptionDTO
    ) {
        try {
            ParameterSubscriptionDTO parameterSubscriptionDTO = parameterSubscriptionService
                    .addParameterSubscription(
                            addParameterSubscriptionDTO.setUserGuid(userGuid)
                    );

            return ResponseEntity
                    .created(
                            new UriBuilder(httpRequest)
                                    .setPathSegment(parameterSubscriptionDTO.getParameterName())
                                    .setPathVariable(
                                            EVENT_NAME_REQ_PARAM,
                                            parameterSubscriptionDTO.getEventName()
                                    )
                                    .setPathVariable(
                                            SERVICE_NAME_REQ_PARAM,
                                            parameterSubscriptionDTO.getServiceName()
                                    ).build()
                    )
                    .body(parameterSubscriptionDTO);
        }
        catch (EntityDoesntExistException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}

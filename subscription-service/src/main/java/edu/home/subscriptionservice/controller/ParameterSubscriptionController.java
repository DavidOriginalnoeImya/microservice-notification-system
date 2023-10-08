package edu.home.subscriptionservice.controller;

import edu.home.notificationsystem.exception.EntityDoesntExistException;
import edu.home.subscriptionservice.controller.util.UriBuilder;
import edu.home.subscriptionservice.dto.UpdateParameterSubscriptionDTO;
import edu.home.subscriptionservice.dto.GetParameterSubscriptionDTO;
import edu.home.subscriptionservice.dto.ParameterSubscriptionDTO;
import edu.home.subscriptionservice.service.ParameterSubscriptionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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

    @PutMapping(consumes = "application/json")
    public ResponseEntity<?> updateParameterSubscription(
            HttpServletRequest httpRequest,
            @RequestBody UpdateParameterSubscriptionDTO updateParameterSubscriptionDTO
    ) {
        try {
            ParameterSubscriptionDTO parameterSubscriptionDTO = parameterSubscriptionService
                    .updateParameterSubscription(
                            updateParameterSubscriptionDTO.setUserGuid(userGuid)
                    );

            return ResponseEntity
                    .ok()
                    .header(
                        HttpHeaders.LOCATION, getLocationUri(
                                httpRequest, parameterSubscriptionDTO.getParameterName(),
                                parameterSubscriptionDTO.getEventName(),
                                parameterSubscriptionDTO.getServiceName()
                        ).toString()
                    )
                    .build();
        }
        catch (EntityDoesntExistException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


    private URI getLocationUri(
            HttpServletRequest httpRequest, String parameterName,
            String eventName, String serviceName
    ) {
        return new UriBuilder(httpRequest)
                .setPathSegment(parameterName)
                .setPathVariable(EVENT_NAME_REQ_PARAM, eventName)
                .setPathVariable(SERVICE_NAME_REQ_PARAM, serviceName)
                .build();
    }

}

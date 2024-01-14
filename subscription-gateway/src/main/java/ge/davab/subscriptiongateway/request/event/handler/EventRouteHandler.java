package ge.davab.subscriptiongateway.request.event.handler;

import ge.davab.subscriptiongateway.request.event.dto.EventDetailsDTO;
import ge.davab.subscriptiongateway.request.event.service.EventService;
import ge.davab.subscriptiongateway.request.event.service.SubscriptionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static ge.davab.subscriptiongateway.request.event.properties.RequestParam.SERVICE_NAME;

@Component
public class EventRouteHandler {

    private final EventService eventService;

    private final SubscriptionService subscriptionService;

    public EventRouteHandler(EventService eventService, SubscriptionService subscriptionService) {
        this.eventService = eventService;
        this.subscriptionService = subscriptionService;
    }

    public Mono<ServerResponse> getEvents(ServerRequest serverRequest) {
        String serviceName = serverRequest
                .queryParam(SERVICE_NAME)
                .orElseThrow();

        var events = eventService.getEvents(serviceName);
        var eventSubscriptions = subscriptionService.getEventSubscriptions(serviceName);
        var eventDetails = Mono.zip(events, eventSubscriptions)
                .map(t -> EventDetailsDTO.create(t.getT1(), t.getT2()));

        return eventDetails.flatMap(eventDetailsDTO -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(eventDetailsDTO))
        );
    }
}

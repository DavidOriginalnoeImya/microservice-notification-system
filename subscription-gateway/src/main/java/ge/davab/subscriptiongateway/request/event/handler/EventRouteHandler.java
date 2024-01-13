package ge.davab.subscriptiongateway.request.event.handler;

import ge.davab.subscriptiongateway.request.event.dto.EventDTO;
import ge.davab.subscriptiongateway.request.event.dto.EventDetailsDTO;
import ge.davab.subscriptiongateway.request.event.dto.EventSubscriptionDTO;
import ge.davab.subscriptiongateway.request.event.service.EventService;
import ge.davab.subscriptiongateway.request.event.service.SubscriptionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
                .queryParam("service-name")
                .orElseThrow();

        Mono<List<EventDTO>> events = eventService.getEvents(serviceName);
        Mono<Set<EventSubscriptionDTO>> eventSubscriptions = subscriptionService
                .getEventSubscriptions(serviceName);
        Mono<Tuple2<List<EventDTO>, Set<EventSubscriptionDTO>>> combined = Mono.zip(
                events, eventSubscriptions
        );
        Mono<List<EventDetailsDTO>> eventDetails = combined.map((t) -> {
            List<EventDetailsDTO> eventDetailList = new LinkedList<>();
            Set<String> eventSub = t.getT2().stream()
                    .map(EventSubscriptionDTO::getEventName)
                    .collect(Collectors.toSet());

            for (EventDTO eventDTO: t.getT1()) {
                eventDetailList.add(new EventDetailsDTO(
                        eventDTO.getName(),
                        eventDTO.getCaption(),
                        eventSub.contains(eventDTO.getName())
                ));
            }

            return eventDetailList;
        });

        return eventDetails.flatMap(eventDetailsDTO -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(eventDetailsDTO))
        );
    }
}

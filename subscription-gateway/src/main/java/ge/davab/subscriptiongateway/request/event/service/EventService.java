package ge.davab.subscriptiongateway.request.event.service;

import ge.davab.subscriptiongateway.request.event.dto.EventDTO;
import ge.davab.subscriptiongateway.request.event.properties.EventServiceProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static ge.davab.subscriptiongateway.request.event.properties.RequestParam.SERVICE_NAME;

@Service
public class EventService {

    private final WebClient webClient;

    private final EventServiceProperties eventServiceProperties;

    public EventService(WebClient.Builder webClientBuilder, EventServiceProperties eventServiceProperties) {
        this.eventServiceProperties = eventServiceProperties;
        this.webClient = webClientBuilder
                .baseUrl(eventServiceProperties.getUrl())
                .build();
    }

    public Mono<List<EventDTO>> getEvents(String serviceName) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(eventServiceProperties.getApiPath())
                        .queryParam(SERVICE_NAME, serviceName)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }
}
package ge.davab.subscriptiongateway.request.event.service;

import ge.davab.subscriptiongateway.request.event.dto.EventDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class EventService {

    private final WebClient webClient;

    public EventService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://localhost:8085")
                .build();
    }

    public Mono<List<EventDTO>> getEvents(String serviceName) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/events")
                        .queryParam("service-name", serviceName)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }
}
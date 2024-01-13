package ge.davab.subscriptiongateway.request.event.service;

import ge.davab.subscriptiongateway.request.event.dto.EventSubscriptionDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Set;

@Service
public class SubscriptionService {

    private final WebClient webClient;

    public SubscriptionService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://localhost:8086")
                .build();
    }

    public Mono<Set<EventSubscriptionDTO>> getEventSubscriptions(String serviceName) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/event-subs")
                        .queryParam("service-name", serviceName)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }
}

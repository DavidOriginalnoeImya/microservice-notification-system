package ge.davab.subscriptiongateway.request.event.service;

import ge.davab.subscriptiongateway.request.event.dto.EventSubscriptionDTO;
import ge.davab.subscriptiongateway.request.event.properties.SubscriptionServiceProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static ge.davab.subscriptiongateway.request.event.properties.RequestParam.SERVICE_NAME;

@Service
public class SubscriptionService {

    private final SubscriptionServiceProperties subscriptionServiceProperties;

    private final WebClient webClient;

    public SubscriptionService(WebClient.Builder webClientBuilder, SubscriptionServiceProperties subscriptionServiceProperties) {
        this.subscriptionServiceProperties = subscriptionServiceProperties;
        this.webClient = webClientBuilder
                .baseUrl(this.subscriptionServiceProperties.getUrl())
                .build();
    }

    public Mono<List<EventSubscriptionDTO>> getEventSubscriptions(String serviceName) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(subscriptionServiceProperties.getApiPath())
                        .queryParam(SERVICE_NAME, serviceName)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }
}

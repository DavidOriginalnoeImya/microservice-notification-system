package ge.davab.subscriptiongateway.request.event.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter @Setter
@Component
@ConfigurationProperties("service.subscription")
public class SubscriptionServiceProperties {

    private String url;

    private String apiPath = "/api/event-subs";
}

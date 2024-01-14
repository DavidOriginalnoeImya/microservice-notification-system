package ge.davab.subscriptiongateway.request.event.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter @Setter
@Component
@ConfigurationProperties(prefix = "service.event")
public class EventServiceProperties {

    private String url;

    private String apiPath = "/api/events";

}

package ge.davab.subscriptionservice.subscription.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class EventSubscriptionDTO {

    private String eventName;

    private String serviceName;

    private String userGuid;
}

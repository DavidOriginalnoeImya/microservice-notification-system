package ge.davab.subscriptiongateway.request.event.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class EventDetailsDTO {

    private String name;

    private String caption;

    private boolean checked;
}

package ge.davab.subscriptionservice.registration.dto;

import ge.davab.subscriptionservice.registration.data.event.Event;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddEventDTO extends AddEntityDTO<Event> {

    private String eventServiceName;

    @Override
    public Event toEntity() {
        Event event = new Event();
        event.setName(getEntityName());
        event.setCaption(getEntityCaption());
        return event;
    }
}

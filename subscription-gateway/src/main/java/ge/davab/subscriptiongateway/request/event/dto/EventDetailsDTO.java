package ge.davab.subscriptiongateway.request.event.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter @Setter
@AllArgsConstructor
public class EventDetailsDTO {

    private String name;

    private String caption;

    private boolean checked;

    public static List<EventDetailsDTO> create(
            List<EventDTO> eventDTOList,
            List<EventSubscriptionDTO> eventSubscriptionDTOList
    ) {
        List<EventDetailsDTO> eventDetailsDTOList = new LinkedList<>();
        Set<String> eventSubscriptionDTOSet = eventSubscriptionDTOList
                .stream()
                .map(EventSubscriptionDTO::getEventName)
                .collect(Collectors.toSet());

        for (EventDTO eventDTO: eventDTOList) {
            eventDetailsDTOList.add(new EventDetailsDTO(
                    eventDTO.getName(),
                    eventDTO.getCaption(),
                    eventSubscriptionDTOSet
                            .contains(eventDTO.getName())));
        }

        return eventDetailsDTOList;
    }
}

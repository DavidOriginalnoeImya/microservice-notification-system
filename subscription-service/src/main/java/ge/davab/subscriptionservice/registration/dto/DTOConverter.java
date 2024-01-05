package ge.davab.subscriptionservice.registration.dto;

import ge.davab.subscriptionservice.registration.data.domainapp.DomainApp;
import ge.davab.subscriptionservice.registration.data.event.Event;
import ge.davab.subscriptionservice.subscription.data.event.EventSubscription;
import ge.davab.subscriptionservice.subscription.dto.EventSubscriptionDTO;

public class DTOConverter {

    public static EventSubscriptionDTO convertToDTO(EventSubscription eventSubscription) {
        EventSubscriptionDTO eventSubscriptionDTO = new EventSubscriptionDTO();
        eventSubscriptionDTO.setEventName(eventSubscription.getEventName());
        eventSubscriptionDTO.setServiceName(eventSubscription.getDomainAppName());

        return eventSubscriptionDTO;
    }

    public static DomainAppDTO convertToDTO(DomainApp domainApp) {
        DomainAppDTO domainAppDTO = new DomainAppDTO();
        domainAppDTO.setServiceName(domainApp.getName());
        domainAppDTO.setServiceCaption(domainApp.getCaption());

        return domainAppDTO;
    }

    public static DomainApp convertFromDTO(AddServiceDTO addServiceDTO) {
        DomainApp domainApp = new DomainApp();
        domainApp.setName(addServiceDTO.getEntityName());
        domainApp.setCaption(addServiceDTO.getEntityCaption());

        return domainApp;
    }

    public static Event convertFromDTO(AddEventDTO addEventDTO) {
        Event event = new Event();
        event.setName(addEventDTO.getEntityName());
        event.setCaption(addEventDTO.getEntityCaption());

        return event;
    }
}

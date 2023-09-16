package edu.home.subscriptionservice.dto;

import edu.home.subscriptionservice.data.domainapp.DomainApp;
import edu.home.subscriptionservice.data.event.Event;

public class DTOConverter {

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

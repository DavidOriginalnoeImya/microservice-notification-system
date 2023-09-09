package edu.home.registrationservice.dto;

import edu.home.registrationservice.data.event.Event;
import edu.home.registrationservice.data.parameter.Parameter;
import edu.home.registrationservice.data.service.DomainApp;
import edu.home.registrationservice.dto.service.AddServiceDTO;

public class DTOConverter {
    public static DomainApp convertFromDTO(AddServiceDTO addServiceDTO) {
        DomainApp domainApp = new DomainApp();
        domainApp.setName(addServiceDTO.getServiceName());
        domainApp.setAddress(addServiceDTO.getServiceAddress());

        return domainApp;
    }

    public static Event convertFromDTO(EventRegistrationDTO eventRegistrationDTO) {
        Event event = new Event();
        event.setName(eventRegistrationDTO.getEventId());

        return event;
    }

    public static Parameter convertFromDTO(ParameterRegistrationDTO parameterRegistrationDTO) {
        Parameter parameter = new Parameter();
        parameter.setName(parameterRegistrationDTO.getParameterId());

        return parameter;
    }

    public static DomainAppDTO convertToDTO(DomainApp domainApp) {
        DomainAppDTO domainAppDTO = new DomainAppDTO();
        domainAppDTO.setDomainAppName(domainApp.getName());
        domainAppDTO.setDomainAppAddress(domainApp.getAddress());

        return domainAppDTO;
    }
}

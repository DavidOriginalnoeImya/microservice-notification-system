package edu.home.registrationservice.dto;

import edu.home.registrationservice.data.event.Event;
import edu.home.registrationservice.data.parameter.Parameter;
import edu.home.registrationservice.data.service.DomainApp;
import edu.home.registrationservice.dto.event.AddEventDTO;
import edu.home.registrationservice.dto.parameter.AddParameterDTO;
import edu.home.registrationservice.dto.service.AddServiceDTO;

import java.util.Set;

public class DTOConverter {
    public static DomainApp convertFromDTO(AddServiceDTO addServiceDTO) {
        DomainApp domainApp = new DomainApp();
        domainApp.setName(addServiceDTO.getServiceName());
        domainApp.setCaption(addServiceDTO.getServiceCaption());
        domainApp.setAddress(addServiceDTO.getServiceAddress());

        return domainApp;
    }

    public static Event convertFromDTO(AddEventDTO addEventDTO) {
        Event event = new Event();
        event.setName(addEventDTO.getEventName());
        event.setCaption(addEventDTO.getEventCaption());

        return event;
    }

    public static DomainAppDTO convertToDTO(DomainApp domainApp) {
        DomainAppDTO domainAppDTO = new DomainAppDTO();
        domainAppDTO.setName(domainApp.getName());
        domainAppDTO.setDomainAppCaption(domainApp.getCaption());

        return domainAppDTO;
    }

    public static EventDTO convertToDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setName(event.getName());
        eventDTO.setCaption(event.getCaption());

        return eventDTO;
    }

    public static ParameterDTO convertToDTO(Parameter parameter) {
        ParameterDTO parameterDTO = new ParameterDTO();
        parameterDTO.setName(parameter.getName());
        parameterDTO.setCaption(parameter.getCaption());
        parameterDTO.setInputType(parameter.getInputType().name());
        parameterDTO.setOptions(parameter.getOptions());


        return parameterDTO;
    }

    public static Parameter convertFromDTO(AddParameterDTO addParameterDTO) {
        Parameter parameter = new Parameter();
        parameter.setName(addParameterDTO.getParameterName());
        parameter.setCaption(addParameterDTO.getParameterCaption());
        parameter.setInputType(Parameter.InputType.valueOf(addParameterDTO.getInputType().name()));
        parameter.setOptions(Set.copyOf(addParameterDTO.getOptions()));

        return parameter;
    }
}

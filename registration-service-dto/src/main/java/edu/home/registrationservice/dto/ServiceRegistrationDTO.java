package edu.home.registrationservice.dto;

import java.util.List;

public class ServiceRegistrationDTO {

    private String serviceName;

    private String serviceAddress;

    private List<EventRegistrationDTO> eventsRegistrationDTO;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public List<EventRegistrationDTO> getEventsRegistrationDTO() {
        return eventsRegistrationDTO;
    }

    public void setEventsRegistrationDTO(List<EventRegistrationDTO> eventsRegistrationDTO) {
        this.eventsRegistrationDTO = eventsRegistrationDTO;
    }
}

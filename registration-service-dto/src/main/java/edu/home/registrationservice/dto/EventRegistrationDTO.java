package edu.home.registrationservice.dto;

import java.util.List;

public class EventRegistrationDTO {

    private String eventId;

    private List<ParameterRegistrationDTO> parametersRegistrationDTO;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public List<ParameterRegistrationDTO> getParametersRegistrationDTO() {
        return parametersRegistrationDTO;
    }

    public void setParametersRegistrationDTO(List<ParameterRegistrationDTO> parametersRegistrationDTO) {
        this.parametersRegistrationDTO = parametersRegistrationDTO;
    }
}

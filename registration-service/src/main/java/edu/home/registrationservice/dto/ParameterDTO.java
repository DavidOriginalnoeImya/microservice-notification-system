package edu.home.registrationservice.dto;

public class ParameterDTO {

    private String parameterName;

    private String parameterEventName;

    private String parameterServiceName;

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterEventName() {
        return parameterEventName;
    }

    public void setParameterEventName(String parameterEventName) {
        this.parameterEventName = parameterEventName;
    }

    public String getParameterServiceName() {
        return parameterServiceName;
    }

    public void setParameterServiceName(String parameterServiceName) {
        this.parameterServiceName = parameterServiceName;
    }
}

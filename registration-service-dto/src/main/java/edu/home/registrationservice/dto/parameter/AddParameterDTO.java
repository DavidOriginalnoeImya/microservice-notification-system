package edu.home.registrationservice.dto.parameter;

import java.util.List;

public class AddParameterDTO {

    private String parameterName;

    private String parameterCaption;

    private String parameterEvent;

    private String parameterDomainService;

    private InputType inputType;

    private List<String> options;

    public enum InputType {
        INPUT, CHECKBOX, SELECT, MULTISELECT
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterCaption() {
        return parameterCaption;
    }

    public void setParameterCaption(String parameterCaption) {
        this.parameterCaption = parameterCaption;
    }

    public String getParameterEvent() {
        return parameterEvent;
    }

    public void setParameterEvent(String parameterEvent) {
        this.parameterEvent = parameterEvent;
    }

    public String getParameterDomainService() {
        return parameterDomainService;
    }

    public void setParameterDomainService(String parameterDomainService) {
        this.parameterDomainService = parameterDomainService;
    }

    public InputType getInputType() {
        return inputType;
    }

    public void setInputType(InputType inputType) {
        this.inputType = inputType;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}

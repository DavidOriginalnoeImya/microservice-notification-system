package edu.home.subscriptionservice.dto;

import edu.home.subscriptionservice.data.parameter.Parameter;

public class AddParameterDTO extends AddEntityDTO {

    private Parameter.InputType parameterInputType;

    private String parameterEventName;

    private String parameterServiceName;

    public Parameter.InputType getParameterInputType() {
        return parameterInputType;
    }

    public void setParameterInputType(Parameter.InputType parameterInputType) {
        this.parameterInputType = parameterInputType;
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

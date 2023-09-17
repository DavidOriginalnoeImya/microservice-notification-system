package edu.home.subscriptionservice.dto;

import edu.home.subscriptionservice.data.parameter.InputType;

import java.util.List;

public class AddParameterDTO extends AddEntityDTO {

    private InputType parameterInputType;

    private List<String> parameterOptions;

    private String parameterEventName;

    private String parameterServiceName;

    public InputType getParameterInputType() {
        return parameterInputType;
    }

    public void setParameterInputType(InputType parameterInputType) {
        this.parameterInputType = parameterInputType;
    }

    public List<String> getParameterOptions() {
        return parameterOptions;
    }

    public void setParameterOptions(List<String> parameterOptions) {
        this.parameterOptions = parameterOptions;
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

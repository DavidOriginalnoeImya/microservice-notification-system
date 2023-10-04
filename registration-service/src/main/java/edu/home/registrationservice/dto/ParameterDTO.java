package edu.home.registrationservice.dto;

import java.util.HashSet;
import java.util.Set;

public class ParameterDTO {

    private String name;

    private Set<String> options = new HashSet<>();

    private String inputType;

    private String caption;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParameterOptions(Set<String> options) {
        this.options = options;
    }

    public Set<String> getOptions() {
        return options;
    }

    public void setParameterInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getInputType() {
        return inputType;
    }


    public void setParameterCaption(String caption) {
        this.caption = caption;
    }

    public String getParameterCaption() {
        return caption;
    }
}

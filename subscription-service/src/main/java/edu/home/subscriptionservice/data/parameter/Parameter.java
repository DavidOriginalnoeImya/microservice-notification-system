package edu.home.subscriptionservice.data.parameter;

import edu.home.subscriptionservice.data.event.Event;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Parameter {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String caption;

    private InputType inputType;

    private boolean defaultChecked;

    @ElementCollection
    private List<String> options = new ArrayList<>();

    @ElementCollection
    private List<String> defaultValues = new ArrayList<>();

    @ManyToOne
    private Event event;

    public enum InputType {

        INPUT,

        CHECKBOX,

        SELECT,

        MULTISELECT
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public InputType getInputType() {
        return inputType;
    }

    public void setInputType(InputType inputType) {
        this.inputType = inputType;
    }

    public boolean isDefaultChecked() {
        return defaultChecked;
    }

    public void setDefaultChecked(boolean defaultChecked) {
        this.defaultChecked = defaultChecked;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<String> getDefaultValues() {
        return defaultValues;
    }

    public void setDefaultValues(List<String> defaultValues) {
        this.defaultValues = defaultValues;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}

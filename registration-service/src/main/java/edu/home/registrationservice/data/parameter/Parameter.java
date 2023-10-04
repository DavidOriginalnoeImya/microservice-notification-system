package edu.home.registrationservice.data.parameter;

import edu.home.registrationservice.data.event.Event;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Parameter {


    public enum InputType {
        INPUT, CHECKBOX, SELECT, MULTISELECT
    }

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String caption;

    private InputType inputType;

    @ElementCollection
    private Set<String> options = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;

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

    public Set<String> getOptions() {
        return options;
    }

    public void setOptions(Set<String> options) {
        this.options = options;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}

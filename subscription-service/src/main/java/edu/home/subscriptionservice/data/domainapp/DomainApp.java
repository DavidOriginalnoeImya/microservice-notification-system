package edu.home.subscriptionservice.data.domainapp;

import edu.home.subscriptionservice.data.Event;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
public class DomainApp {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String caption;

    @OneToMany(cascade = { PERSIST, REMOVE })
    private List<Event> events = new ArrayList<>();

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

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }
}

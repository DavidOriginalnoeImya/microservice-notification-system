package edu.home.subscriptionservice.data.event;

import edu.home.subscriptionservice.data.domainapp.DomainApp;
import edu.home.subscriptionservice.data.parameter.Parameter;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String caption;

    @ManyToOne(fetch = FetchType.LAZY)
    private DomainApp domainApp;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
    private Set<Parameter> parameters = new HashSet<>();

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

    public DomainApp getService() {
        return domainApp;
    }

    public void setService(DomainApp domainApp) {
        this.domainApp = domainApp;
    }

    public Set<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(Set<Parameter> parameters) {
        this.parameters = parameters;
    }
}

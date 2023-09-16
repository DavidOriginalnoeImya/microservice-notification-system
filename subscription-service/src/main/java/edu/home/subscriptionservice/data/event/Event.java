package edu.home.subscriptionservice.data.event;

import edu.home.subscriptionservice.data.domainapp.DomainApp;
import jakarta.persistence.*;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String caption;

    private boolean defaultChecked;

    @ManyToOne(fetch = FetchType.LAZY)
    private DomainApp domainApp;

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

    public boolean isDefaultChecked() {
        return defaultChecked;
    }

    public void setDefaultChecked(boolean defaultChecked) {
        this.defaultChecked = defaultChecked;
    }
}

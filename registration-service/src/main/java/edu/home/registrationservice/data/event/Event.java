package edu.home.registrationservice.data.event;

import edu.home.registrationservice.data.service.DomainApp;
import jakarta.persistence.*;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
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

    public DomainApp getDomainApp() {
        return domainApp;
    }

    public void setDomainApp(DomainApp domainApp) {
        this.domainApp = domainApp;
    }
}

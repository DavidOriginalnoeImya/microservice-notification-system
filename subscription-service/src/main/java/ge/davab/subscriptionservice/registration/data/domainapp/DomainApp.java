package ge.davab.subscriptionservice.registration.data.domainapp;

import jakarta.persistence.*;

@Entity
@Table(name = "service")
public class DomainApp {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String caption;

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
}

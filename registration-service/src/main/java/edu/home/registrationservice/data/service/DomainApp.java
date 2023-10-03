package edu.home.registrationservice.data.service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "service")
public class DomainApp {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String caption;

    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String locator) {
        this.address = locator;
    }
}

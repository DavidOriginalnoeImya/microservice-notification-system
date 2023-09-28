package edu.home.subscriptionservice.data.user;

import edu.home.subscriptionservice.data.parameter.Parameter;
import edu.home.subscriptionservice.data.subscription.parameter.ParameterSubscription;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ns_user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String guid;

    @OneToMany(mappedBy = "user")
    private List<ParameterSubscription> parameterSubscriptions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public boolean hasSubscription(Parameter parameter) {
        return parameterSubscriptions.stream()
                .anyMatch((ps) -> Objects.equals(ps.getParameter(), parameter) &&
                        Objects.equals(ps.getUser(), this));
    }
}

package ge.davab.subscriptionservice.subscription.data.user;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "ns_user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String guid;

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
}

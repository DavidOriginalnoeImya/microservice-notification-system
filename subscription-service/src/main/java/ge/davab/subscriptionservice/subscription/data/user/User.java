package ge.davab.subscriptionservice.subscription.data.user;

import ge.davab.subscriptionservice.subscription.data.event.EventSubscription;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter @Getter
public class User {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    private String guid;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "user")
    private Set<EventSubscription> eventSubscriptions = new HashSet<>();
}

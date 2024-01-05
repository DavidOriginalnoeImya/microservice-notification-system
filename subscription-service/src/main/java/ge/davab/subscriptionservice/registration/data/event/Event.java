package ge.davab.subscriptionservice.registration.data.event;

import ge.davab.subscriptionservice.registration.data.domainapp.DomainApp;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Event {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    private String name;

    private String caption;

    @ManyToOne(fetch = FetchType.LAZY)
    private DomainApp domainApp;

    public String getServiceName() {
        return domainApp.getName();
    }
}

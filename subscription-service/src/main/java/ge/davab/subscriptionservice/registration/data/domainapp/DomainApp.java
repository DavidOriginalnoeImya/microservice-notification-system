package ge.davab.subscriptionservice.registration.data.domainapp;

import ge.davab.subscriptionservice.registration.dto.DomainAppDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "service")
@Getter @Setter
public class DomainApp {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    private String name;

    private String caption;

    public DomainAppDTO toDTO() {
        return new DomainAppDTO(getName(), getCaption());
    }
}

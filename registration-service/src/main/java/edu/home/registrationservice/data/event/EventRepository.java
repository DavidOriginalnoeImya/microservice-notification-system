package edu.home.registrationservice.data.event;

import edu.home.registrationservice.data.service.DomainApp;
import edu.home.registrationservice.dto.EventDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    boolean existsByName(String name);

    Optional<Event> getByNameAndDomainAppName(String name, String domainAppName);

    List<Event> getByDomainApp(DomainApp domainApp);
}

package edu.home.registrationservice.data.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    boolean existsByName(String name);

    Optional<Event> getByNameAndDomainAppName(String name, String domainAppName);
}

package edu.home.subscriptionservice.data.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> getByNameAndDomainAppName(String name, String domainAppName);
}

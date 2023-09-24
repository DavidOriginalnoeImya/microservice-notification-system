package edu.home.subscriptionservice.data.parameter;

import edu.home.subscriptionservice.data.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParameterRepository extends JpaRepository<Parameter, Long> {

    Optional<Parameter> getByNameAndEvent(String name, Event event);
}

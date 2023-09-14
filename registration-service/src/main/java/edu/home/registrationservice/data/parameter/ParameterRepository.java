package edu.home.registrationservice.data.parameter;

import edu.home.registrationservice.data.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParameterRepository extends JpaRepository<Parameter, Long> {

    boolean existsByNameAndEvent(String name, Event event);

    Optional<Parameter> getByNameAndEvent(String name, Event event);

}

package edu.home.registrationservice.data.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DomainAppRepository extends JpaRepository<DomainApp, Long> {

    boolean existsByName(String name);

    Optional<DomainApp> getByName(String name);
}

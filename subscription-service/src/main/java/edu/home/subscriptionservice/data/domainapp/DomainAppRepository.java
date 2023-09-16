package edu.home.subscriptionservice.data.domainapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DomainAppRepository extends JpaRepository<DomainApp, Long> {

    Optional<DomainApp> findByName(String name);

}

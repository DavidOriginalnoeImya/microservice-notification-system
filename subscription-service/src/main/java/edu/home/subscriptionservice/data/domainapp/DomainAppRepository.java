package edu.home.subscriptionservice.data.domainapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainAppRepository extends JpaRepository<DomainApp, Long> {
}

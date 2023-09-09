package edu.home.registrationservice.data.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainAppRepository extends JpaRepository<DomainApp, Long> {

    boolean existsByNameAndAddress(String name, String address);

    DomainApp getByName(String name);
}

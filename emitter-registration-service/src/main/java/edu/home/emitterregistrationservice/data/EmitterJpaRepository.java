package edu.home.emitterregistrationservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface EmitterJpaRepository extends JpaRepository<Emitter, Long> {
}

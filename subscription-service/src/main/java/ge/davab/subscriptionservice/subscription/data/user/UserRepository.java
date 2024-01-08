package ge.davab.subscriptionservice.subscription.data.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getByGuid(String guid);

    @Query(
        "select u from User u " +
        "join u.eventSubscriptions es " +
        "join es.event e " +
        "join e.domainApp s " +
        "where e.name = ?1 and s.name = ?2"
    )
    List<User> getByEventSubscription(String eventName, String serviceName);
}

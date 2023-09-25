package edu.home.subscriptionservice.data.subscription;

import edu.home.subscriptionservice.data.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventSubscriptionRepository extends JpaRepository<EventSubscription, Id> {


    // check what sql is generated for
    Optional<EventSubscription> getByEventAndUserGuid(Event event, String userGuid);


}

package ge.davab.subscriptionservice.subscription.data.event;

import ge.davab.subscriptionservice.registration.data.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EventSubscriptionRepository extends JpaRepository<EventSubscription, Id> {


    @Query("select es from EventSubscription es where es.event = ?1 and es.user.guid = ?2")
    Optional<EventSubscription> getByEventAndUserGuid(Event event, String userGuid);

    @Query("select es from EventSubscription es where es.user.guid = ?1 and es.event.domainApp.name = ?2")
    List<EventSubscription> getByUserGuidAndDomainAppName(String userGuid, String domainAppName);
}

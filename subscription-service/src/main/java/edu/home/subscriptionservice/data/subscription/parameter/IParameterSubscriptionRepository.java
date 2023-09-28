package edu.home.subscriptionservice.data.subscription.parameter;

import edu.home.subscriptionservice.data.event.Event;
import edu.home.subscriptionservice.data.parameter.Parameter;
import edu.home.subscriptionservice.data.subscription.Id;
import edu.home.subscriptionservice.data.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IParameterSubscriptionRepository<T extends ParameterSubscription>
        extends JpaRepository<T, Id> {

    @Query("select ps from #{#entityName} ps where ps.user = ?1 and ps.parameter = ?2")
    Optional<T> getByUserAndParameter(User user, Parameter parameter);

    @Query("select ps from #{#entityName} ps where ps.user = ?1 and ps.parameter.event = ?2")
    List<T> getByUserAndEvent(User user, Event event);
}

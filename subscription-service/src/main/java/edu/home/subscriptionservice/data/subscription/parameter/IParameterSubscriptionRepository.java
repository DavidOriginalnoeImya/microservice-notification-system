package edu.home.subscriptionservice.data.subscription.parameter;

import edu.home.subscriptionservice.data.parameter.Parameter;
import edu.home.subscriptionservice.data.subscription.Id;
import edu.home.subscriptionservice.data.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface IParameterSubscriptionRepository<T extends ParameterSubscription>
        extends JpaRepository<T, Id> {

    @Query("select ps from #{#entityName} ps where ps.user = ?1 and ps.parameter = ?2")
    Optional<T> getByUserAndParameter(User user, Parameter parameter);

}

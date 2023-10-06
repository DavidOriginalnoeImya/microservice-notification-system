package edu.home.subscriptionservice.data.subscription.parameter;

import edu.home.subscriptionservice.data.parameter.Parameter;
import edu.home.subscriptionservice.data.user.User;
import edu.home.subscriptionservice.dto.ParameterSubscriptionDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SSPS")
public class SingleStringParameterSubscription extends ParameterSubscription {

    private String value;

    protected SingleStringParameterSubscription() {}

    public SingleStringParameterSubscription(
            User user, Parameter parameter, String value
    ) {
        super(user, parameter);
        setValue(value);
    }

    @Override
    public ParameterSubscriptionDTO toDTO() {
        return null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

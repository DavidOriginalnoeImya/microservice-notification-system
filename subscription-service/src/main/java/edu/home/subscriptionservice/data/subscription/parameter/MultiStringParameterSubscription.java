package edu.home.subscriptionservice.data.subscription.parameter;

import edu.home.subscriptionservice.data.parameter.Parameter;
import edu.home.subscriptionservice.data.user.User;
import edu.home.subscriptionservice.dto.MultiStringParameterSubscriptionDTO;
import edu.home.subscriptionservice.dto.ParameterSubscriptionDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("MSPS")
class MultiStringParameterSubscription extends ParameterSubscription {

    @ElementCollection
    private List<String> valueList = new ArrayList<>();

    protected MultiStringParameterSubscription() {}

    protected MultiStringParameterSubscription(
            User user, Parameter parameter, List<String> values
    ) {
        super(user, parameter);
        this.valueList = values;
    }

    @Override
    public ParameterSubscriptionDTO toDTO() {
        return new MultiStringParameterSubscriptionDTO()
                .setValues(valueList)
                .setParameterName(getParameterName());
    }

    public List<String> getValues() {
        return valueList;
    }

    public void setValues(List<String> values) {
        this.valueList = values;
    }

}

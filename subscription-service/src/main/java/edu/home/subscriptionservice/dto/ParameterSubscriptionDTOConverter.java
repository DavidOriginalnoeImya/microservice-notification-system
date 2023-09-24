package edu.home.subscriptionservice.dto;

import edu.home.subscriptionservice.data.subscription.parameter.ParameterSubscription;

@FunctionalInterface
public interface ParameterSubscriptionDTOConverter
        <T extends ParameterSubscriptionValueDTO<?>> {

    ParameterSubscription convertFromDTO(T dto);
}

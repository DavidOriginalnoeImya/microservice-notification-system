package edu.home.subscriptionservice.service;

import edu.home.subscriptionservice.data.event.Event;
import edu.home.subscriptionservice.data.event.EventRepository;
import edu.home.subscriptionservice.data.parameter.Parameter;
import edu.home.subscriptionservice.data.parameter.ParameterRepository;
import edu.home.subscriptionservice.data.subscription.parameter.MultiStringParameterSubscriptionRepository;
import edu.home.subscriptionservice.data.subscription.parameter.ParameterSubscription;
import edu.home.subscriptionservice.data.subscription.parameter.IParameterSubscriptionRepository;
import edu.home.subscriptionservice.data.user.User;
import edu.home.subscriptionservice.data.user.UserRepository;
import edu.home.subscriptionservice.dto.*;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

import static edu.home.subscriptionservice.data.parameter.Parameter.InputType.MULTISELECT;

@Service
public class ParameterSubscriptionService {

    private final Map<
            Parameter.InputType,
            Class<? extends IParameterSubscriptionRepository<?>>>
    repositoryMap = Map.of(
            MULTISELECT, MultiStringParameterSubscriptionRepository.class
    );

    private final ApplicationContext applicationContext;

    private final ParameterRepository parameterRepository;

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    public ParameterSubscriptionService(
            ApplicationContext applicationContext,
            ParameterRepository parameterRepository,
            EventRepository eventRepository,
            UserRepository userRepository
    ) {
        this.applicationContext = applicationContext;
        this.parameterRepository = parameterRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public ParameterSubscriptionDTO getParameterSubscription(
            GetParameterSubscriptionDTO getParameterSubscriptionDTO
    ) {
        User user = getUser(getParameterSubscriptionDTO.getUserGuid());

        Parameter parameter = getParameter(
                getParameterSubscriptionDTO.getParameterName(),
                getParameterSubscriptionDTO.getEventName(),
                getParameterSubscriptionDTO.getDomainAppName()
        );

        return applicationContext
                .getBean(repositoryMap.get(parameter.getInputType()))
                .getByUserAndParameter(user, parameter)
                .map(ParameterSubscription::toDTO)
                .orElseThrow();
    }

//    @Transactional
//    public ParameterSubscriptionDTO addParameterSubscription(
//            AddParameterSubscriptionDTO<?> addParameterSubscriptionDTO) {
//        User user = getUser(addParameterSubscriptionDTO.getUserGuid());
//
//        Parameter parameter = getParameter(
//                addParameterSubscriptionDTO.getParameterName(),
//                addParameterSubscriptionDTO.getEventName(),
//                addParameterSubscriptionDTO.getDomainAppName()
//        );
//
//        ParameterSubscription parameterSubscription = ParameterSubscription
//                .getParameterSubscription(user, parameter, addParameterSubscriptionDTO);
//
//        return applicationContext
//                .getBean(repositoryMap.get(parameter.getInputType()))
//                .save(parameterSubscription)
//
//    }

    private User getUser(String userGuid) {
        return userRepository
                .getByGuid(userGuid)
                .orElseThrow();
    }

    private Parameter getParameter(String parameterName, Event event) {
        return parameterRepository
                .getByNameAndEvent(parameterName, event)
                .orElseThrow();
    }

    private Parameter getParameter(
            String parameterName, String eventName, String domainAppName
    ) {
        Event event = getEvent(eventName, domainAppName);
        return getParameter(parameterName, event);
    }

    private Event getEvent(String eventName, String domainAppName) {
        return eventRepository
                .getByNameAndDomainAppName(eventName, domainAppName)
                .orElseThrow();
    }
}

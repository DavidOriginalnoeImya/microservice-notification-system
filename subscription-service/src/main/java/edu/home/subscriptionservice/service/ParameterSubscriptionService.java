package edu.home.subscriptionservice.service;

import edu.home.subscriptionservice.data.event.Event;
import edu.home.subscriptionservice.data.event.EventRepository;
import edu.home.subscriptionservice.data.parameter.Parameter;
import edu.home.subscriptionservice.data.parameter.ParameterRepository;
import edu.home.subscriptionservice.data.subscription.parameter.IParameterSubscriptionRepository;
import edu.home.subscriptionservice.data.subscription.parameter.ParameterSubscription;
import edu.home.subscriptionservice.data.user.User;
import edu.home.subscriptionservice.data.user.UserRepository;
import edu.home.subscriptionservice.dto.AddParameterSubscriptionDTO;
import edu.home.subscriptionservice.dto.DTOConverter;
import edu.home.subscriptionservice.dto.GetParameterSubscriptionDTO;
import edu.home.subscriptionservice.dto.ParameterSubscriptionDTO;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ParameterSubscriptionService {

    private final ParameterRepository parameterRepository;

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    private final IParameterSubscriptionRepository<ParameterSubscription> parameterSubscriptionRepository;

    public ParameterSubscriptionService(
            ParameterRepository parameterRepository,
            EventRepository eventRepository,
            UserRepository userRepository,
            IParameterSubscriptionRepository<ParameterSubscription> parameterSubscriptionRepository
    ) {
        this.parameterRepository = parameterRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.parameterSubscriptionRepository = parameterSubscriptionRepository;
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

        return parameterSubscriptionRepository
                .getByUserAndParameter(user, parameter)
                .map(ParameterSubscription::toDTO)
                .orElseThrow();
    }

    @Transactional
    public List<ParameterSubscriptionDTO> getParameterSubscriptions(
            String userGuid, String eventName, String domainAppName
    ) {
        User user = getUser(userGuid);

        Event event = getEvent(eventName, domainAppName);

        return parameterSubscriptionRepository
                .getByUserAndEvent(user, event)
                .stream().map(ParameterSubscription::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ParameterSubscriptionDTO addParameterSubscription(
            AddParameterSubscriptionDTO addParameterSubscriptionDTO
    ) {
        User user = getUser(addParameterSubscriptionDTO.getUserGuid());

        Parameter parameter = getParameter(
                addParameterSubscriptionDTO.getParameterName(),
                addParameterSubscriptionDTO.getEventName(),
                addParameterSubscriptionDTO.getDomainAppName()
        );

        ParameterSubscription ps = ParameterSubscription
                .getParameterSubscription(user, parameter, addParameterSubscriptionDTO);

        return DTOConverter
                .convertToDTO(parameterSubscriptionRepository.save(ps));
    }

    List<ParameterSubscriptionDTO> addParameterSubscriptions(
            User user, Set<Parameter> parameters
    ) {
        List<ParameterSubscriptionDTO> parameterSubscriptionsDTO = new LinkedList<>();

        for (Parameter parameter: parameters) {
            ParameterSubscription parameterSubscription = ParameterSubscription
                    .getParameterSubscription(user, parameter);

            if (!parameterSubscriptionRepository.existsById(parameterSubscription.getId())) {
                parameterSubscriptionsDTO.add(
                        DTOConverter.convertToDTO(
                                parameterSubscriptionRepository.save(parameterSubscription)
                        )
                );
            }
        }

        return parameterSubscriptionsDTO;
    }

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

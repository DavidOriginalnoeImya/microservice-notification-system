package edu.home.subscriptionservice.service;

import edu.home.notificationsystem.exception.EntityAlreadyExistsException;
import edu.home.notificationsystem.exception.EntityDoesntExistException;
import edu.home.subscriptionservice.data.event.Event;
import edu.home.subscriptionservice.data.event.EventRepository;
import edu.home.subscriptionservice.data.parameter.Parameter;
import edu.home.subscriptionservice.data.parameter.ParameterRepository;
import edu.home.subscriptionservice.data.subscription.parameter.IParameterSubscriptionRepository;
import edu.home.subscriptionservice.data.subscription.parameter.ParameterSubscription;
import edu.home.subscriptionservice.data.user.User;
import edu.home.subscriptionservice.data.user.UserRepository;
import edu.home.subscriptionservice.dto.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ParameterSubscriptionService {

    public static final String PARAM_SUB_DOESNT_EXIST = "Parameter subscription doesn't exist";
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

    @Transactional
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
                .orElseThrow(() -> new EntityDoesntExistException(PARAM_SUB_DOESNT_EXIST));
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
    public ParameterSubscriptionDTO updateParameterSubscription(
            UpdateParameterSubscriptionDTO addParameterSubscriptionDTO
    ) {
        User user = getUser(addParameterSubscriptionDTO.getUserGuid());

        Parameter parameter = getParameter(
                addParameterSubscriptionDTO.getParameterName(),
                addParameterSubscriptionDTO.getEventName(),
                addParameterSubscriptionDTO.getDomainAppName()
        );

        ParameterSubscription ps = ParameterSubscription
                .getParameterSubscription(user, parameter, addParameterSubscriptionDTO);

        return parameterSubscriptionRepository.save(ps).toDTO();
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

        if (!parameterSubscriptionRepository.existsByUserAndParameter(user, parameter)) {
            ParameterSubscription parameterSubscription = ParameterSubscription
                    .getParameterSubscription(user, parameter);

            return DTOConverter.convertToDTO(
                    parameterSubscriptionRepository.save(parameterSubscription)
            );
        }
        else throw new EntityAlreadyExistsException("User already subscribed to this parameter");
    }

    @Transactional
    public void deleteParameterSubscription(
            DeleteParameterSubscriptionDTO deleteParameterSubscriptionDTO
    ) {
        User user = getUser(deleteParameterSubscriptionDTO.getUserGuid());

        Parameter parameter = getParameter(
                deleteParameterSubscriptionDTO.getParameterName(),
                deleteParameterSubscriptionDTO.getEventName(),
                deleteParameterSubscriptionDTO.getDomainAppName()
        );

        ParameterSubscription ps = parameterSubscriptionRepository
                .getByUserAndParameter(user, parameter)
                .orElseThrow(() -> new EntityDoesntExistException(PARAM_SUB_DOESNT_EXIST));

        parameterSubscriptionRepository.delete(ps);
    }

    private User getUser(String userGuid) {
        return userRepository
                .getByGuid(userGuid)
                .orElseThrow(() -> new EntityDoesntExistException("User doesn't exist"));
    }

    private Parameter getParameter(String parameterName, Event event) {
        return parameterRepository
                .getByNameAndEvent(parameterName, event)
                .orElseThrow(() -> new EntityDoesntExistException("Parameter doesn't exist"));
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
                .orElseThrow(() -> new EntityDoesntExistException("Event doesn't exist"));
    }
}

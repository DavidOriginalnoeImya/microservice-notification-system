package edu.home.registrationservice.service;

import edu.home.registrationservice.data.event.Event;
import edu.home.registrationservice.data.event.EventRepository;
import edu.home.registrationservice.data.parameter.Parameter;
import edu.home.registrationservice.data.parameter.ParameterRepository;
import edu.home.registrationservice.dto.DTOConverter;
import edu.home.registrationservice.dto.ParameterDTO;
import edu.home.registrationservice.dto.parameter.AddParameterDTO;
import edu.home.registrationservice.exception.EntityAlreadyExistsException;
import edu.home.registrationservice.exception.EntityDoesntExistException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParameterService {

    private final ParameterRepository parameterRepository;

    private final EventRepository eventRepository;

    public ParameterService(
            ParameterRepository parameterRepository,
            EventRepository eventRepository
    ) {
        this.parameterRepository = parameterRepository;
        this.eventRepository = eventRepository;
    }

    @Transactional
    public ParameterDTO addParameter(AddParameterDTO addParameterDTO) {
        Event event = getEvent(
                addParameterDTO.getParameterEvent(),
                addParameterDTO.getParameterDomainService()
        );

        if (!parameterRepository.existsByNameAndEvent(addParameterDTO.getParameterName(), event)) {
            Parameter parameter = DTOConverter.convertFromDTO(addParameterDTO);
            parameter.setEvent(event);
            parameterRepository.save(parameter);
            return DTOConverter.convertToDTO(parameter);
        }

        throw new EntityAlreadyExistsException("Parameter with same name already exists");
    }

    @Transactional
    public List<ParameterDTO> getParameters(String eventName, String domainAppName) {
        Event event = getEvent(eventName, domainAppName);

        return parameterRepository.getByEvent(event)
                .stream()
                .map(DTOConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ParameterDTO getParameter(String parameterName, String eventName, String serviceName) {
        Event event = getEvent(eventName, serviceName);

        Parameter parameter = parameterRepository
                .getByNameAndEvent(parameterName, event)
                .orElseThrow(() -> new EntityDoesntExistException("Parameter with this name doesn't exist"));

        return DTOConverter.convertToDTO(parameter);
    }

    private Event getEvent(String eventName, String serviceName) {
        return eventRepository
                .getByNameAndDomainAppName(eventName, serviceName)
                .orElseThrow(() -> new EntityDoesntExistException("Parent entity doesn't exist"));
    }
}

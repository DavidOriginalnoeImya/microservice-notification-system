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
        Event event = eventRepository
                .getByNameAndDomainAppName(
                        addParameterDTO.getParameterEvent(),
                        addParameterDTO.getParameterDomainService()
                )
                .orElseThrow(() -> new EntityDoesntExistException("Parent entity doesn't exist"));

        if (!parameterRepository.existsByNameAndEvent(addParameterDTO.getParameterName(), event)) {
            Parameter parameter = DTOConverter.convertFromDTO(addParameterDTO);
            parameter.setEvent(event);
            parameterRepository.save(parameter);
            return DTOConverter.convertToDTO(parameter);
        }

        throw new EntityAlreadyExistsException("Parameter with same name already exists");
    }

    @Transactional
    public List<ParameterDTO> getParameters() {
        return parameterRepository.findAll()
                .stream()
                .map(DTOConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ParameterDTO getParameter(String parameterName, String eventName, String serviceName) {
        Event event = eventRepository
                .getByNameAndDomainAppName(eventName, serviceName)
                .orElseThrow(() -> new EntityDoesntExistException("Parent entity doesn't exist"));

        Parameter parameter = parameterRepository
                .getByNameAndEvent(parameterName, event)
                .orElseThrow(() -> new EntityDoesntExistException("Parameter with this name doesn't exist"));

        return DTOConverter.convertToDTO(parameter);
    }
}

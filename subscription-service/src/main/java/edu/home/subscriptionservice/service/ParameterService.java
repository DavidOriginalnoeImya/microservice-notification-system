package edu.home.subscriptionservice.service;

import edu.home.subscriptionservice.data.event.Event;
import edu.home.subscriptionservice.data.event.EventRepository;
import edu.home.subscriptionservice.data.parameter.Parameter;
import edu.home.subscriptionservice.data.parameter.ParameterRepository;
import edu.home.subscriptionservice.dto.AddParameterDTO;
import edu.home.subscriptionservice.dto.DTOConverter;
import org.springframework.stereotype.Service;

@Service
public class ParameterService implements EntityService<AddParameterDTO> {

    private final ParameterRepository parameterRepository;

    private final EventRepository eventRepository;

    public ParameterService(
            ParameterRepository parameterRepository,
            EventRepository eventRepository
    ) {
        this.parameterRepository = parameterRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public void add(AddParameterDTO addParameterDTO) {
        Event event = eventRepository
                .getByNameAndDomainAppName(
                        addParameterDTO.getParameterEventName(),
                        addParameterDTO.getParameterServiceName()
                )
                .orElseThrow();

        Parameter parameter = DTOConverter.convertFromDTO(addParameterDTO);
        parameter.setEvent(event);
        parameterRepository.save(parameter);
    }
}

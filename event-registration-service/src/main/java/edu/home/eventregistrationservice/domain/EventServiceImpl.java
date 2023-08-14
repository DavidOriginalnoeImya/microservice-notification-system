package edu.home.eventregistrationservice.domain;

import org.springframework.stereotype.Service;

@Service
class EventServiceImpl implements EventService {

    private EventBroker eventBroker;

    public EventServiceImpl(EventBroker eventBroker) {
        this.eventBroker = eventBroker;
    }

    @Override
    public void registerEvent(EventDTO eventDTO) {

    }
}

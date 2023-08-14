package edu.home.eventregistrationservice.web;

import edu.home.eventregistrationservice.domain.EventService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/event")
public class EventRest {

    private final EventService eventService;

    public EventRest(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public void addEvent(EventDTO eventDTO) {
        eventService.registerEvent(eventDTO);
    }
}

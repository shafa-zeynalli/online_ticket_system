package com.example.online_ticketing_system.adapter.controller.event;


import com.example.online_ticketing_system.application.dto.event.EventCreateDTO;
import com.example.online_ticketing_system.application.dto.event.EventResponseDTO;
import com.example.online_ticketing_system.application.dto.event.EventUpdateDTO;
import com.example.online_ticketing_system.domain.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventResponseDTO> getEvent() {
        return eventService.getAll();
    }

    @GetMapping("/{id}")
    public EventResponseDTO getEventById(@PathVariable Long id) {
        return eventService.getById(id);
    }

    @PostMapping
    public EventResponseDTO createEvent(@RequestBody EventCreateDTO eventCreateDTO) {
        return eventService.create(eventCreateDTO);
    }

    @PutMapping("/{id}")
    public EventResponseDTO updateEvent(@PathVariable Long id, @RequestBody EventUpdateDTO eventUpdateDTO) {
        return eventService.update(id,eventUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEventById(@PathVariable Long id) {
        eventService.delete(id);
    }

}

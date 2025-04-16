package com.example.online_ticketing_system.adapter.controller.event;


import com.example.online_ticketing_system.application.dto.event.event_hall.EventHallCreateDTO;
import com.example.online_ticketing_system.application.dto.event.event_hall.EventHallResponseDTO;
import com.example.online_ticketing_system.application.dto.event.event_hall.EventHallUpdateDTO;
import com.example.online_ticketing_system.domain.service.EventHallService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event-halls")
public class EventHallController {

    private final EventHallService eventHallService;

    public EventHallController(EventHallService eventHallService) {
        this.eventHallService = eventHallService;
    }

    @GetMapping
    public List<EventHallResponseDTO> findAll() {
        return eventHallService.getAllEventHall();
    }

    @GetMapping("/{id}")
    public EventHallResponseDTO findById(@PathVariable Long id) {
        return eventHallService.getEventHallById(id);
    }

    @PostMapping
    public EventHallResponseDTO create(@RequestBody EventHallCreateDTO eventHallCreateDTO) {
        return eventHallService.createEventHall(eventHallCreateDTO);
    }

    @PutMapping("/{id}")
    public EventHallResponseDTO update(@PathVariable Long id, @RequestBody EventHallUpdateDTO eventHallUpdateDTO) {
        return eventHallService.update(id, eventHallUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
         eventHallService.delete(id);
    }
}

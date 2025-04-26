package com.example.online_ticketing_system.adapter.controller;


import com.example.online_ticketing_system.application.dto.ticket.ticket_type.EventTicketTypeCreateDTO;
import com.example.online_ticketing_system.application.dto.ticket.ticket_type.EventTicketTypeResponseDTO;
import com.example.online_ticketing_system.application.dto.ticket.ticket_type.EventTicketTypeUpdateDTO;
import com.example.online_ticketing_system.domain.service.EventTicketTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/event-ticket-type")
public class EventTicketTypeController {

    private final EventTicketTypeService eventTicketTypeService;

    @Autowired
    public EventTicketTypeController(EventTicketTypeService eventTicketTypeService) {
        this.eventTicketTypeService = eventTicketTypeService;
    }

    @GetMapping
    public List<EventTicketTypeResponseDTO> getEventTicketTypes() {
        return eventTicketTypeService.findAll();
    }

    @GetMapping("/{id}")
    public EventTicketTypeResponseDTO getEventTicketTypeById(@PathVariable Long id) {
        return eventTicketTypeService.findById(id);
    }

    @PostMapping
    public EventTicketTypeResponseDTO postEventTicketType(@RequestBody EventTicketTypeCreateDTO eventTicketTypeCreateDTO) {
        return eventTicketTypeService.create(eventTicketTypeCreateDTO);
    }

    @PutMapping("/{id}")
    public EventTicketTypeResponseDTO putEventTicketType(@PathVariable Long id,
                                                         @RequestBody EventTicketTypeUpdateDTO eventTicketTypeUpdateDTO) {
        return eventTicketTypeService.update(id, eventTicketTypeUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEventTicketType(@PathVariable Long id) {
        eventTicketTypeService.deleteById(id);
    }
}

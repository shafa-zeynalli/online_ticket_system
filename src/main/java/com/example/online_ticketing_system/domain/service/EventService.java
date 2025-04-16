package com.example.online_ticketing_system.domain.service;


import com.example.online_ticketing_system.application.dto.event.EventCreateDTO;
import com.example.online_ticketing_system.application.dto.event.EventResponseDTO;
import com.example.online_ticketing_system.application.dto.event.EventUpdateDTO;

import java.util.List;

public interface EventService {
    List<EventResponseDTO> getAll();
    EventResponseDTO getById(Long id);
    EventResponseDTO create(EventCreateDTO dto);
    EventResponseDTO update(Long id, EventUpdateDTO dto);
    void delete(Long id);
}

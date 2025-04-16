package com.example.online_ticketing_system.domain.service;



import com.example.online_ticketing_system.application.dto.event.EventResponseDTO;
import com.example.online_ticketing_system.application.dto.event.EventUpdateDTO;
import com.example.online_ticketing_system.application.dto.event.event_hall.EventHallCreateDTO;
import com.example.online_ticketing_system.application.dto.event.event_hall.EventHallResponseDTO;
import com.example.online_ticketing_system.application.dto.event.event_hall.EventHallUpdateDTO;

import java.util.List;

public interface EventHallService {
    EventHallResponseDTO getEventHallById(Long id);
    EventHallResponseDTO createEventHall(EventHallCreateDTO eventHallCreateDTO);
    List<EventHallResponseDTO> getAllEventHall();
    EventHallResponseDTO findByName(String name);
    EventHallResponseDTO update(Long id, EventHallUpdateDTO dto);
    void delete(Long id);

}

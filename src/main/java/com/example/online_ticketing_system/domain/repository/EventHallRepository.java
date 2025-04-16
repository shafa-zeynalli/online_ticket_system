package com.example.online_ticketing_system.domain.repository;

import com.example.online_ticketing_system.application.dto.event.EventResponseDTO;
import com.example.online_ticketing_system.application.dto.event.EventUpdateDTO;
import com.example.online_ticketing_system.domain.model.EventHall;

import java.util.List;
import java.util.Optional;

public interface EventHallRepository {
    EventHall save(EventHall event);
    Optional<EventHall> findById(Long id);
    List<EventHall> findAll();
    void delete(Long id);
    EventHall findByName(String name);
    EventHall update(Long id, EventHall dto);

}

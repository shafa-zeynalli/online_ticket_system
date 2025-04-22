package com.example.online_ticketing_system.domain.repository;

import com.example.online_ticketing_system.domain.model.EventHall;
import com.example.online_ticketing_system.domain.model.EventTicketType;

import java.util.List;
import java.util.Optional;

public interface EventTicketTypeRepository {
    EventTicketType save(EventTicketType eventTicketType);
    Optional<EventTicketType> findById(Long id);
    List<EventTicketType> findAll();
    void delete(Long id);
}

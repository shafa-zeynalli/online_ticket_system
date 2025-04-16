package com.example.online_ticketing_system.domain.repository;

import com.example.online_ticketing_system.domain.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepository {
    Event save(Event event);
    Optional<Event> findById(Long id);
    List<Event> findAll();
    void delete(Long id);
}

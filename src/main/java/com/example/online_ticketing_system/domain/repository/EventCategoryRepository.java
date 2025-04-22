package com.example.online_ticketing_system.domain.repository;

import com.example.online_ticketing_system.domain.model.EventCategory;

import java.util.List;
import java.util.Optional;

public interface EventCategoryRepository {
    EventCategory save(EventCategory eventCategory);
    Optional<EventCategory> findById(Long id);
    List<EventCategory> findAll();
    EventCategory findByName(String name);
    EventCategory update(Long id, EventCategory eventCategory);
    void delete(Long id);
}

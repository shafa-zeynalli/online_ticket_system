package com.example.online_ticketing_system.infrastructure.persistence.impl;

import com.example.online_ticketing_system.domain.model.EventCategory;
import com.example.online_ticketing_system.domain.model.EventHall;
import com.example.online_ticketing_system.domain.repository.EventCategoryRepository;
import com.example.online_ticketing_system.infrastructure.persistence.repository.EventCategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EventCategoryRepositoryImpl implements EventCategoryRepository {

    private final EventCategoryJpaRepository eventCategoryJpaRepository;

    @Override
    public EventCategory save(EventCategory eventCategory) {
        return eventCategoryJpaRepository.save(eventCategory);
    }

    @Override
    public Optional<EventCategory> findById(Long id) {
        return eventCategoryJpaRepository.findById(id);
    }

    @Override
    public List<EventCategory> findAll() {
        return eventCategoryJpaRepository.findAll();
    }

    @Override
    public EventCategory findByName(String name) {
        return null;
    }

    @Override
    public EventCategory update(Long id, EventCategory eventCategory) {
        return eventCategoryJpaRepository.save(eventCategory);
    }

    @Override
    public void delete(Long id) {
        eventCategoryJpaRepository.deleteById(id);
    }
}

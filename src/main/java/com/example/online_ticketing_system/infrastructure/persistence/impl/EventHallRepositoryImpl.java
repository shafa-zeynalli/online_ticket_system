package com.example.online_ticketing_system.infrastructure.persistence.impl;

import com.example.online_ticketing_system.domain.model.EventHall;
import com.example.online_ticketing_system.domain.repository.EventHallRepository;
import com.example.online_ticketing_system.infrastructure.persistence.repository.EventHallJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EventHallRepositoryImpl implements EventHallRepository {

    private final EventHallJpaRepository eventHallJpaRepository;


    @Override
    public EventHall save(EventHall event) {
        return eventHallJpaRepository.save(event);
    }

    @Override
    public Optional<EventHall> findById(Long id) {
        return eventHallJpaRepository.findById(id);
    }

    @Override
    public List<EventHall> findAll() {
        return new ArrayList<>(eventHallJpaRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        eventHallJpaRepository.deleteById(id);
    }

    @Override
    public EventHall findByName(String name) {
        return eventHallJpaRepository.findByName(name);
    }

    @Override
    public EventHall update(Long id, EventHall dto) {
        return eventHallJpaRepository.save(dto);
    }

}

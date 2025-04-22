package com.example.online_ticketing_system.infrastructure.persistence.impl;


import com.example.online_ticketing_system.domain.model.EventTicketType;
import com.example.online_ticketing_system.domain.repository.EventTicketTypeRepository;
import com.example.online_ticketing_system.infrastructure.persistence.repository.EventTicketTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EventTicketTypeRepositoryImpl implements EventTicketTypeRepository {

    private final EventTicketTypeJpaRepository eventTicketTypeJpaRepository;

    @Override
    public EventTicketType save(EventTicketType eventTicketType) {
        return eventTicketTypeJpaRepository.save(eventTicketType);
    }

    @Override
    public Optional<EventTicketType> findById(Long id) {
        return eventTicketTypeJpaRepository.findById(id);
    }

    @Override
    public List<EventTicketType> findAll() {
        return eventTicketTypeJpaRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        eventTicketTypeJpaRepository.deleteById(id);
    }

}

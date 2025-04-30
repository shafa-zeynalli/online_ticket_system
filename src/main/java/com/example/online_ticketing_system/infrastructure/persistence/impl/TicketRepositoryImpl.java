package com.example.online_ticketing_system.infrastructure.persistence.impl;

import com.example.online_ticketing_system.domain.enums.TicketStatus;
import com.example.online_ticketing_system.domain.model.Ticket;
import com.example.online_ticketing_system.domain.repository.TicketRepository;
import com.example.online_ticketing_system.infrastructure.persistence.repository.TicketJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {

    private final TicketJpaRepository ticketJpaRepository;


    @Override
    public List<Ticket> findAll() {
        return ticketJpaRepository.findAll();
    }

    @Override
    public List<Ticket> findAllById(List<Long> ticketIds) {
        return ticketJpaRepository.findAllById(ticketIds);
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return ticketJpaRepository.findById(id);
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketJpaRepository.save(ticket);
    }

    @Override
    public void delete(Ticket ticket) {
        ticketJpaRepository.delete(ticket);
    }
}

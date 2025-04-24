package com.example.online_ticketing_system.domain.repository;

import com.example.online_ticketing_system.domain.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {
    List<Ticket> findAll();
    Optional<Ticket> findById(Long id);
    Ticket save(Ticket ticket);
    void delete(Ticket ticket);
}

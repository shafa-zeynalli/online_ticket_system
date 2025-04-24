package com.example.online_ticketing_system.infrastructure.persistence.repository;

import com.example.online_ticketing_system.domain.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketJpaRepository extends JpaRepository<Ticket, Long> {
}

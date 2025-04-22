package com.example.online_ticketing_system.infrastructure.persistence.repository;

import com.example.online_ticketing_system.domain.model.EventTicketType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTicketTypeJpaRepository extends JpaRepository<EventTicketType, Long> {
}

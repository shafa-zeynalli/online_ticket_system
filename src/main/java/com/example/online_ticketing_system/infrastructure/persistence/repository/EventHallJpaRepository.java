package com.example.online_ticketing_system.infrastructure.persistence.repository;

import com.example.online_ticketing_system.domain.model.EventHall;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventHallJpaRepository extends JpaRepository<EventHall, Long> {
    EventHall findByName(String name);
}

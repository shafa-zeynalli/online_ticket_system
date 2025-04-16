package com.example.online_ticketing_system.infrastructure.persistence.repository;

import com.example.online_ticketing_system.domain.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventJpaRepository extends JpaRepository<Event, Long> {
}

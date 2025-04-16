package com.example.online_ticketing_system.infrastructure.persistence.repository;

import com.example.online_ticketing_system.domain.model.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCategoryJpaRepository extends JpaRepository<EventCategory, Long> {
}

package com.example.online_ticketing_system.infrastructure.persistence.repository;

import com.example.online_ticketing_system.domain.model.SeatLock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SeatLockJpaRepository extends JpaRepository<SeatLock, Integer> {
    Optional<SeatLock> findBySeatNumberAndEvent_IdAndExpiresAtAfter(Integer seatNumber, Long eventId, LocalDateTime now);
    Optional<SeatLock> findBySeatNumberAndEvent_IdAndUser_Id(Integer seatNumber, Long eventId, Long userId);
    List<SeatLock> findAllByExpiresAtBefore(LocalDateTime now);
}

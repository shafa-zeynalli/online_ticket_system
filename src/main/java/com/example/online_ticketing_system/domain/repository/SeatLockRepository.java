package com.example.online_ticketing_system.domain.repository;

import com.example.online_ticketing_system.domain.model.SeatLock;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SeatLockRepository {
    Optional<SeatLock> findActiveLock(Integer seatNumber, Long eventId);
    Optional<SeatLock> findBySeatNumberAndEventAndUser(Integer seatNumber, Long eventId, Long userId);
    void save(SeatLock seatLock);
    void delete(SeatLock seatLock);
    List<SeatLock> findExpiredLocks(LocalDateTime now);
}

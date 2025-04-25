package com.example.online_ticketing_system.domain.service;

import com.example.online_ticketing_system.application.dto.seat_lock.SeatLockCreateDTO;

public interface SeatLockService {
    boolean isSeatLocked(Integer seatNumber, Long eventId);
    void lockSeat(SeatLockCreateDTO seatLockCreateDTO);
    void unlockSeat(Integer seatNumber, Long eventId, Long userId);
    void clearExpiredLocks();
}

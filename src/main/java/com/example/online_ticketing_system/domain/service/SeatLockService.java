package com.example.online_ticketing_system.domain.service;

import com.example.online_ticketing_system.application.dto.seat_lock.SeatLockCreateDTO;
import com.example.online_ticketing_system.domain.model.User;

public interface SeatLockService {
    boolean isSeatLocked(Integer seatNumber, Long eventId);
    void lockSeat(SeatLockCreateDTO seatLockCreateDTO, User user);
    void unlockSeat(Integer seatNumber, Long eventId, Long userId);
    void clearExpiredLocks();
}

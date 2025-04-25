package com.example.online_ticketing_system.infrastructure.persistence.impl;

import com.example.online_ticketing_system.domain.model.SeatLock;
import com.example.online_ticketing_system.domain.repository.SeatLockRepository;
import com.example.online_ticketing_system.domain.service.SeatLockService;
import com.example.online_ticketing_system.infrastructure.persistence.repository.SeatLockJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SeatLockRepositoryImpl implements SeatLockRepository {

    private final SeatLockJpaRepository jpaRepository;

    @Override
    public Optional<SeatLock> findActiveLock(Integer seatNumber, Long eventId) {
        return jpaRepository.findBySeatNumberAndEvent_IdAndExpiresAtAfter(seatNumber, eventId, LocalDateTime.now());
    }

    @Override
    public Optional<SeatLock> findBySeatNumberAndEventAndUser(Integer seatNumber, Long eventId, Long userId) {
        return jpaRepository.findBySeatNumberAndEvent_IdAndUser_Id(seatNumber, eventId, userId);
    }

    @Override
    public void save(SeatLock seatLock) {
        jpaRepository.save(seatLock);
    }

    @Override
    public void delete(SeatLock seatLock) {
        jpaRepository.delete(seatLock);
    }

    @Override
    public List<SeatLock> findExpiredLocks(LocalDateTime now) {
        return jpaRepository.findAllByExpiresAtBefore(now);
    }
}

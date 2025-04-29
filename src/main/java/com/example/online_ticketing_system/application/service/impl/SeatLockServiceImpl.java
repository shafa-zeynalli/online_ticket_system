package com.example.online_ticketing_system.application.service.impl;

import com.example.online_ticketing_system.application.dto.seat_lock.SeatLockCreateDTO;
import com.example.online_ticketing_system.application.mapper.SeatLockMapper;
import com.example.online_ticketing_system.domain.exception.ResourceNotFoundException;
import com.example.online_ticketing_system.domain.exception.SeatAlreadyLockedException;
import com.example.online_ticketing_system.domain.model.Event;
import com.example.online_ticketing_system.domain.model.SeatLock;
import com.example.online_ticketing_system.domain.model.User;
import com.example.online_ticketing_system.domain.repository.EventRepository;
import com.example.online_ticketing_system.domain.repository.SeatLockRepository;
import com.example.online_ticketing_system.domain.service.SeatLockService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class SeatLockServiceImpl implements SeatLockService {

    private final SeatLockRepository seatLockRepository;
    private final SeatLockMapper seatLockMapper;
    private final EventRepository eventRepository;

    public SeatLockServiceImpl(SeatLockRepository seatLockRepository,
                               SeatLockMapper seatLockMapper,
                               EventRepository eventRepository) {
        this.seatLockRepository = seatLockRepository;
        this.seatLockMapper = seatLockMapper;
        this.eventRepository = eventRepository;
    }


    @Override
    public boolean isSeatLocked(Integer seatNumber, Long eventId) {
        return seatLockRepository.findActiveLock(seatNumber,eventId).isPresent();
    }

    @Override
    public void lockSeat(SeatLockCreateDTO dto, @AuthenticationPrincipal User user) {
        if (isSeatLocked(dto.getSeatNumber(), dto.getEventId())) {
            throw new SeatAlreadyLockedException("Seat already locked");
        }
        SeatLock seatLock = seatLockMapper.toEntity(dto);

        Event event = eventRepository.findById(dto.getEventId()).orElseThrow(() -> new ResourceNotFoundException("Event not found"));
        seatLock.setEvent(event);
        seatLock.setUser(user);
        seatLock.setLockedAt(LocalDateTime.now());
        seatLock.setExpiresAt(LocalDateTime.now().plusMinutes(1));
        seatLock.setReason("Temporary");

        seatLockRepository.save(seatLock);
    }

    @Override
    public void unlockSeat(Integer seatNumber, Long eventId, Long userId) {
        seatLockRepository.findBySeatNumberAndEventAndUser(seatNumber, eventId, userId).ifPresent(seatLockRepository::delete);
    }

    @Override
    public void clearExpiredLocks() {
        seatLockRepository.findExpiredLocks(LocalDateTime.now()).forEach(seatLockRepository::delete);
    }
}

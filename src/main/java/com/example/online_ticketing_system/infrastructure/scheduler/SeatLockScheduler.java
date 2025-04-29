package com.example.online_ticketing_system.infrastructure.scheduler;

import com.example.online_ticketing_system.domain.model.SeatLock;
import com.example.online_ticketing_system.domain.repository.SeatLockRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SeatLockScheduler {

    private final SeatLockRepository seatLockRepository;

    public SeatLockScheduler(SeatLockRepository seatLockRepository) {
        this.seatLockRepository = seatLockRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void clearExpiredSeatLocks() {
        LocalDateTime now = LocalDateTime.now();

//        seatLockRepository.findExpiredLocks(now).forEach(seatLock -> {
//            if (seatLock.isPaymentCompleted()) seatLockRepository.delete(seatLock);
//        });  //bu da bir yoldu amma daha yaxshisini ashagida yazacagimdi

        List<SeatLock> expiredLocks = seatLockRepository.findExpiredLocks(now);
        List<SeatLock> deletableLocks = expiredLocks.stream().filter(lock -> !lock.isPaymentCompleted()).toList();

        if (!deletableLocks.isEmpty()) seatLockRepository.deleteAll(deletableLocks);
    }
}

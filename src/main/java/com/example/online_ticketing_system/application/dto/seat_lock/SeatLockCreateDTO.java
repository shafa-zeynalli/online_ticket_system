package com.example.online_ticketing_system.application.dto.seat_lock;


import com.example.online_ticketing_system.domain.model.Event;
import com.example.online_ticketing_system.domain.model.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatLockCreateDTO {
    private Integer seatNumber;
    private Long eventId;
    private Long userId;
    private LocalDateTime lockedAt;
    private LocalDateTime expiresAt;
    private String reason;
}

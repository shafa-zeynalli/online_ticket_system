package com.example.online_ticketing_system.application.dto.seat_lock;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatLockUpdateDTO {
    private Integer seatNumber;
    private Long eventId;
    private Long userId;
    private LocalDateTime lockedAt;
    private LocalDateTime expiresAt;
    private String reason;
}

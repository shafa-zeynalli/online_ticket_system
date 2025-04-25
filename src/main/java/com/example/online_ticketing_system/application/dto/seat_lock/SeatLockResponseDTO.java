package com.example.online_ticketing_system.application.dto.seat_lock;


import com.example.online_ticketing_system.application.dto.event.EventResponseDTO;
import com.example.online_ticketing_system.application.dto.user.UserResponseDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatLockResponseDTO {
    private Long id;
    private Integer seatNumber;
    private EventResponseDTO event;
    private UserResponseDTO user;
    private LocalDateTime lockedAt;
    private LocalDateTime expiresAt;
    private String reason;
}

package com.example.online_ticketing_system.application.dto.ticket;

import com.example.online_ticketing_system.domain.enums.TicketStatus;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketCreateDTO {

    private BigDecimal price;
    private Integer seatNumber;
    private String qrCode;

    private Long eventId;
    private Long ticketTypeId;
    private Long SeatLockId;

    private TicketStatus status;
}

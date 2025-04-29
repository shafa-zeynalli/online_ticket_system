package com.example.online_ticketing_system.application.dto.ticket;

import com.example.online_ticketing_system.domain.enums.TicketStatus;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketUpdateDTO {

    private BigDecimal price;
    private Integer seatNumber;
    private String qrCode;

    private Long eventId;
//    private UserResponseDTO user;
    private Long ticketTypeId;

    private TicketStatus status;
}

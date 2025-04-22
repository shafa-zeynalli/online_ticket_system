package com.example.online_ticketing_system.application.dto.ticket;

import com.example.online_ticketing_system.application.dto.event.EventResponseDTO;
import com.example.online_ticketing_system.application.dto.ticket.ticket_type.EventTicketTypeResponseDTO;
import com.example.online_ticketing_system.application.dto.user.UserResponseDTO;
import com.example.online_ticketing_system.domain.enums.TicketStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketUpdateDTO {

    private LocalDateTime purchaseDate;
    private BigDecimal price;
    private Integer seatNumber;
    private String qrCode;

    private EventResponseDTO event;
    private UserResponseDTO user;
    private EventTicketTypeResponseDTO ticketType;

    private TicketStatus status;
}

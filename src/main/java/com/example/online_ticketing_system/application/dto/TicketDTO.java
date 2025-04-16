package com.example.online_ticketing_system.application.dto;

import com.example.online_ticketing_system.application.dto.event.EventCreateDTO;
import com.example.online_ticketing_system.application.dto.user.UserResponseDTO;
import com.example.online_ticketing_system.domain.enums.TicketStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TicketDTO{

    private LocalDateTime purchaseDate;
    private BigDecimal price;
    private Integer seatNumber;
    private String qrCode;

    private EventCreateDTO event;
    private UserResponseDTO user;
    private TicketTypeDTO ticketType;

    private TicketStatus status;
}

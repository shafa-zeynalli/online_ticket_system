package com.example.online_ticketing_system.application.dto.ticket.ticket_type;


import com.example.online_ticketing_system.application.dto.event.EventResponseDTO;
import com.example.online_ticketing_system.domain.enums.TicketType;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventTicketTypeResponseDTO {

    private Long id;
    private String name;
    private String description;

    private TicketType ticketType;

    private EventResponseDTO event;
}

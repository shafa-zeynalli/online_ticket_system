package com.example.online_ticketing_system.application.dto.ticket.ticket_type;

import com.example.online_ticketing_system.domain.enums.TicketType;
import com.example.online_ticketing_system.domain.model.Event;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventTicketTypeCreateDTO {

    private String name;
    private String description;

    private TicketType ticketType;

    private Long eventId;
}

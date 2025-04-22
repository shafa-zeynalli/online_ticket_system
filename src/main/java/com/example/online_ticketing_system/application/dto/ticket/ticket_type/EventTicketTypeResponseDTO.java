package com.example.online_ticketing_system.application.dto.ticket.ticket_type;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventTicketTypeResponseDTO {

    private Long id;
    private String name;
}

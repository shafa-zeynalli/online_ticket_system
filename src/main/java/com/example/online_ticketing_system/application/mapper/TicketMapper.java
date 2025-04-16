package com.example.online_ticketing_system.application.mapper;


import com.example.online_ticketing_system.application.dto.TicketDTO;
import com.example.online_ticketing_system.domain.model.Ticket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    TicketDTO toDTO(Ticket ticket);
    Ticket   toEntity(TicketDTO ticketDTO);
}

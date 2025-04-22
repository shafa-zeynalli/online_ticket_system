package com.example.online_ticketing_system.application.mapper;


import com.example.online_ticketing_system.application.dto.ticket.TicketCreateDTO;
import com.example.online_ticketing_system.application.dto.ticket.TicketResponseDTO;
import com.example.online_ticketing_system.application.dto.ticket.TicketUpdateDTO;
import com.example.online_ticketing_system.domain.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    TicketResponseDTO toDTO(Ticket ticket);
    Ticket toEntity(TicketCreateDTO dto);

    void updateEntityFromDTO(TicketUpdateDTO dto, @MappingTarget Ticket ticket);
}

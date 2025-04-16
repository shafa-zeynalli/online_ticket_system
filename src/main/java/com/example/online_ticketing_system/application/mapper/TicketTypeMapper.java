package com.example.online_ticketing_system.application.mapper;


import com.example.online_ticketing_system.application.dto.TicketTypeDTO;
import com.example.online_ticketing_system.domain.model.EventTicketType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketTypeMapper {

    TicketTypeDTO toDTO(EventTicketType ticketType);
    EventTicketType toEntity(TicketTypeDTO ticketTypeDTO);
}

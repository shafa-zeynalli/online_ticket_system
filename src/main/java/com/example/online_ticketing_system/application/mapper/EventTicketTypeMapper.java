package com.example.online_ticketing_system.application.mapper;


import com.example.online_ticketing_system.application.dto.ticket.ticket_type.EventTicketTypeCreateDTO;
import com.example.online_ticketing_system.application.dto.ticket.ticket_type.EventTicketTypeResponseDTO;
import com.example.online_ticketing_system.application.dto.ticket.ticket_type.EventTicketTypeUpdateDTO;
import com.example.online_ticketing_system.domain.model.EventTicketType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EventTicketTypeMapper {

    EventTicketTypeResponseDTO toDTO(EventTicketType ticketType);

    EventTicketType toEntity(EventTicketTypeCreateDTO ticketTypeDTO);

    void updateDTO(EventTicketTypeUpdateDTO dto, @MappingTarget EventTicketType entity);
}

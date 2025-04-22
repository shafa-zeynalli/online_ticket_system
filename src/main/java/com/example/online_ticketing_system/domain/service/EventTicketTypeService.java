package com.example.online_ticketing_system.domain.service;

import com.example.online_ticketing_system.application.dto.ticket.ticket_type.EventTicketTypeCreateDTO;
import com.example.online_ticketing_system.application.dto.ticket.ticket_type.EventTicketTypeResponseDTO;
import com.example.online_ticketing_system.application.dto.ticket.ticket_type.EventTicketTypeUpdateDTO;
import com.example.online_ticketing_system.domain.model.EventTicketType;

import java.util.List;

public interface EventTicketTypeService {
    EventTicketTypeResponseDTO create(EventTicketTypeCreateDTO dto);
    List<EventTicketTypeResponseDTO> findAll();
    EventTicketTypeResponseDTO findById(Long id);
    void deleteById(Long id);
    EventTicketTypeResponseDTO update(Long id, EventTicketTypeUpdateDTO dto);
}

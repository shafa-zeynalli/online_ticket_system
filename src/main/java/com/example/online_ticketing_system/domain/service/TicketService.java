package com.example.online_ticketing_system.domain.service;

import com.example.online_ticketing_system.application.dto.ticket.TicketCreateDTO;
import com.example.online_ticketing_system.application.dto.ticket.TicketResponseDTO;
import com.example.online_ticketing_system.application.dto.ticket.TicketUpdateDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TicketService {
    List<TicketResponseDTO> findAllTickets();
    Optional<TicketResponseDTO> findTicketById(Long id);
    TicketResponseDTO saveTicket(TicketCreateDTO ticket);
    TicketResponseDTO updateTicket(Long id, TicketUpdateDTO ticket);
    void deleteTicket(Long id);
    Map<String, List<TicketResponseDTO>> getTicketsGroupedByStatus();

}

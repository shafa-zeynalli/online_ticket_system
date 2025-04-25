package com.example.online_ticketing_system.adapter.controller;


import com.example.online_ticketing_system.application.dto.ticket.TicketCreateDTO;
import com.example.online_ticketing_system.application.dto.ticket.TicketResponseDTO;
import com.example.online_ticketing_system.application.dto.ticket.TicketUpdateDTO;
import com.example.online_ticketing_system.domain.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<TicketResponseDTO> getAllTickets() {
        return ticketService.findAllTickets();
    }

    @GetMapping("/{id}")
    public TicketResponseDTO getTicket(@PathVariable("id") Long id) {
        return ticketService.findTicketById(id).orElse(null);
    }

    @GetMapping("/grouped-by-status")
    public ResponseEntity<Map<String, List<TicketResponseDTO>>> getTicketsGroupedByStatus() {
        return ResponseEntity.ok(ticketService.getTicketsGroupedByStatus());
    }

    @PostMapping
    public TicketResponseDTO createTicket(@RequestBody TicketCreateDTO ticketCreateDTO) {
        return ticketService.saveTicket(ticketCreateDTO);
    }

    @PutMapping("/{id}")
    public TicketResponseDTO updateTicket(@PathVariable Long id,@RequestBody TicketUpdateDTO ticketUpdateDTO) {
        return ticketService.updateTicket(id, ticketUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
    }

}

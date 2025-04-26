package com.example.online_ticketing_system.application.service.impl;

import com.example.online_ticketing_system.application.dto.ticket.TicketCreateDTO;
import com.example.online_ticketing_system.application.dto.ticket.TicketResponseDTO;
import com.example.online_ticketing_system.application.dto.ticket.TicketUpdateDTO;
import com.example.online_ticketing_system.application.mapper.TicketMapper;
import com.example.online_ticketing_system.domain.enums.TicketStatus;
import com.example.online_ticketing_system.domain.exception.AlreadyDeletedException;
import com.example.online_ticketing_system.domain.exception.ResourceNotFoundException;
import com.example.online_ticketing_system.domain.model.Event;
import com.example.online_ticketing_system.domain.model.EventTicketType;
import com.example.online_ticketing_system.domain.model.Ticket;
import com.example.online_ticketing_system.domain.model.User;
import com.example.online_ticketing_system.domain.repository.*;
import com.example.online_ticketing_system.domain.service.TicketService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketMapper ticketMapper;
    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventTicketTypeRepository eventTicketTypeRepository;

    public TicketServiceImpl(TicketMapper ticketMapper,
                             TicketRepository ticketRepository,
                             EventRepository eventRepository, UserRepository userRepository,
                             EventTicketTypeRepository eventTicketTypeRepository) {
        this.ticketMapper = ticketMapper;
        this.ticketRepository = ticketRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.eventTicketTypeRepository = eventTicketTypeRepository;
    }

    @Override
    public List<TicketResponseDTO> findAllTickets() {
        return ticketRepository.findAll().stream().map(ticketMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<TicketResponseDTO> findTicketById(Long id) {
        return  ticketRepository.findById(id).map(ticketMapper::toDTO);
    }

    @Override
    public TicketResponseDTO saveTicket(TicketCreateDTO ticketCreateDTO) {
        Event event = eventRepository.findById(ticketCreateDTO.getEventId())
                .orElseThrow(() -> new ResourceNotFoundException("Event not found!"));
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new ResourceNotFoundException("User not found!"));
        EventTicketType eventTicketType = eventTicketTypeRepository.findById(ticketCreateDTO.getTicketTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Ticket type not found!"));

        Ticket ticket = ticketMapper.toEntity(ticketCreateDTO);
        ticket.setUser(user);
        ticket.setTicketType(eventTicketType);
        ticket.setEvent(event);

        return ticketMapper.toDTO(ticketRepository.save(ticket));
    }

    @Override
    public TicketResponseDTO updateTicket(Long id, TicketUpdateDTO ticketUpdateDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new ResourceNotFoundException("User not found!"));

        Ticket currentTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found!"));
        Event event = eventRepository.findById(ticketUpdateDTO.getEventId())
                .orElseThrow(() -> new ResourceNotFoundException("Event not found!"));
        EventTicketType eventTicketType = eventTicketTypeRepository.findById(ticketUpdateDTO.getTicketTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Ticket type not found!"));
        ticketMapper.updateEntityFromDTO(ticketUpdateDTO,currentTicket);

        currentTicket.setEvent(event);
        currentTicket.setTicketType(eventTicketType);

        return  ticketMapper.toDTO(ticketRepository.save(currentTicket));
    }

    @Override
    public void deleteTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found!"));
        if (ticket.getDeletedAt() != null) {
            throw new AlreadyDeletedException("Ticket deleted at " + ticket.getDeletedAt());
        }
        ticketRepository.delete(ticket);
    }

    @Override
    public Map<String, List<TicketResponseDTO>> getTicketsGroupedByStatus() {
        List<Ticket> tickets = ticketRepository.findAll();
        Map<String, List<TicketResponseDTO>> ticketsByStatus = new HashMap<>();

        for (Ticket ticket : tickets) {
            TicketStatus status = ticket.getStatus();
            ticketsByStatus.computeIfAbsent(status.name(), k -> new ArrayList<>()).add(ticketMapper.toDTO(ticket));
        }
        return ticketsByStatus;
    }
}

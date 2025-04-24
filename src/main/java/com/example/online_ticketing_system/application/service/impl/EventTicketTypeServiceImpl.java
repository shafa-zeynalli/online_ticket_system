package com.example.online_ticketing_system.application.service.impl;

import com.example.online_ticketing_system.application.dto.ticket.ticket_type.EventTicketTypeCreateDTO;
import com.example.online_ticketing_system.application.dto.ticket.ticket_type.EventTicketTypeResponseDTO;
import com.example.online_ticketing_system.application.dto.ticket.ticket_type.EventTicketTypeUpdateDTO;
import com.example.online_ticketing_system.application.mapper.EventTicketTypeMapper;
import com.example.online_ticketing_system.domain.model.Event;
import com.example.online_ticketing_system.domain.model.EventTicketType;
import com.example.online_ticketing_system.domain.repository.EventRepository;
import com.example.online_ticketing_system.domain.repository.EventTicketTypeRepository;
import com.example.online_ticketing_system.domain.service.EventTicketTypeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EventTicketTypeServiceImpl implements EventTicketTypeService {

    private final EventTicketTypeMapper eventTicketTypeMapper;

    private final EventTicketTypeRepository eventTicketTypeRepository;
    private final EventRepository eventRepository;


    @Autowired
    public EventTicketTypeServiceImpl(EventTicketTypeMapper eventTicketTypeMapper,
                                      EventTicketTypeRepository eventTicketTypeRepository, EventRepository eventRepository) {
        this.eventTicketTypeMapper = eventTicketTypeMapper;
        this.eventTicketTypeRepository = eventTicketTypeRepository;
        this.eventRepository = eventRepository;
    }


    @Override
    public EventTicketTypeResponseDTO create(EventTicketTypeCreateDTO eventTicketTypeCreateDTO) {
        Event event = eventRepository.findById(eventTicketTypeCreateDTO.getEventId()).orElseThrow(() -> new EntityNotFoundException("Event not found"));
        EventTicketType eventTicketType = eventTicketTypeMapper.toEntity(eventTicketTypeCreateDTO);
        eventTicketType.setEvent(event);
        return eventTicketTypeMapper.toDTO(
                eventTicketTypeRepository.save(eventTicketType)
        );
    }

    @Override
    public List<EventTicketTypeResponseDTO> findAll() {
        return eventTicketTypeRepository.findAll().stream().map(eventTicketTypeMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public EventTicketTypeResponseDTO findById(Long id) {
        return eventTicketTypeRepository.findById(id).map(eventTicketTypeMapper::toDTO).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        EventTicketType eventTicketType = eventTicketTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Event ticket type not found!"));
        if (eventTicketType.getDeletedAt() != null) {
            throw new EntityNotFoundException("Event ticket type already deleted!");
        }        
        eventTicketTypeRepository.delete(eventTicketType.getId());
    }

    @Override
    public EventTicketTypeResponseDTO update(Long id, EventTicketTypeUpdateDTO eventTicketType) {
        EventTicketType currentEventTicketType = eventTicketTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Event ticket type not found!"));
        Event event = eventRepository.findById(eventTicketType.getEventId()).orElseThrow(() -> new EntityNotFoundException("Event not found!"));
        eventTicketTypeMapper.updateDTO(eventTicketType, currentEventTicketType);
        currentEventTicketType.setEvent(event);
        return eventTicketTypeMapper.toDTO(eventTicketTypeRepository.save(currentEventTicketType));
    }
}

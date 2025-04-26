package com.example.online_ticketing_system.application.service.impl;

import com.example.online_ticketing_system.application.dto.event.EventCreateDTO;
import com.example.online_ticketing_system.application.dto.event.EventResponseDTO;
import com.example.online_ticketing_system.application.dto.event.EventUpdateDTO;
import com.example.online_ticketing_system.application.mapper.EventMapper;
import com.example.online_ticketing_system.domain.exception.AlreadyDeletedException;
import com.example.online_ticketing_system.domain.exception.ResourceNotFoundException;
import com.example.online_ticketing_system.domain.model.Event;
import com.example.online_ticketing_system.domain.model.EventCategory;
import com.example.online_ticketing_system.domain.model.EventHall;
import com.example.online_ticketing_system.domain.model.User;
import com.example.online_ticketing_system.domain.repository.EventCategoryRepository;
import com.example.online_ticketing_system.domain.repository.EventHallRepository;
import com.example.online_ticketing_system.domain.repository.EventRepository;
import com.example.online_ticketing_system.domain.repository.UserRepository;
import com.example.online_ticketing_system.domain.service.EventService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventMapper eventMapper;

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final EventCategoryRepository eventCategoryRepository;
    private final EventHallRepository eventHallRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper, UserRepository userRepository, EventCategoryRepository eventCategoryRepository, EventHallRepository eventHallRepository) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.userRepository = userRepository;
        this.eventCategoryRepository = eventCategoryRepository;
        this.eventHallRepository = eventHallRepository;
    }

    public List<EventResponseDTO> getAll() {
        return eventRepository.findAll()
                .stream()
                .map(eventMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public EventResponseDTO getById(Long id) {
        return  eventRepository.findById(id)
                .map(eventMapper::toResponseDTO)
                .orElse(null);
    }

    public EventResponseDTO create(EventCreateDTO eventCreateDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(()-> new ResourceNotFoundException("User not found!"));

        EventCategory category = eventCategoryRepository.findById(eventCreateDTO.getEventCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Event category not found!"));

        EventHall hall = eventHallRepository.findById(eventCreateDTO.getEventHallId())
                .orElseThrow(() -> new ResourceNotFoundException("Event hall not found!"));

        Event event = eventMapper.toEntity(eventCreateDTO);
        event.setCreatedBy(currentUser);
        event.setEventCategory(category);
        event.setEventHall(hall);

        Event savedEvent = eventRepository.save(event);
        return eventMapper.toResponseDTO(savedEvent);
    }

    @Override
    public EventResponseDTO update(Long eventId ,EventUpdateDTO eventUpdateDTO) {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        User currentUser = userRepository.findByUsername(username)
//                .orElseThrow(()-> new UsernameNotFoundException("User not found!"));

        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(()-> new ResourceNotFoundException("Event not found!"));

        EventCategory category = eventCategoryRepository.findById(eventUpdateDTO.getEventCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Event category not found!"));

        EventHall hall = eventHallRepository.findById(eventUpdateDTO.getEventHallId())
                .orElseThrow(() -> new ResourceNotFoundException("Event hall not found!"));

        eventMapper.updateEntityFromDTO(eventUpdateDTO, existingEvent);

        existingEvent.setEventCategory(category);
        existingEvent.setEventHall(hall);
        Event updatedEvent = eventRepository.save(existingEvent);
        return eventMapper.toResponseDTO(updatedEvent);
    }

    @Override
    public void delete(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found!"));
        if (event.getDeletedAt() != null) {
            throw new AlreadyDeletedException("Event already deleted");
        }
        eventRepository.delete(event.getId());
    }
}

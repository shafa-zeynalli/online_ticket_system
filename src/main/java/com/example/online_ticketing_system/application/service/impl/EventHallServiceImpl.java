package com.example.online_ticketing_system.application.service.impl;


import com.example.online_ticketing_system.application.dto.event.event_hall.EventHallCreateDTO;
import com.example.online_ticketing_system.application.dto.event.event_hall.EventHallResponseDTO;
import com.example.online_ticketing_system.application.dto.event.event_hall.EventHallUpdateDTO;
import com.example.online_ticketing_system.application.mapper.EventHallMapper;
import com.example.online_ticketing_system.domain.exception.AlreadyDeletedException;
import com.example.online_ticketing_system.domain.exception.ResourceNotFoundException;
import com.example.online_ticketing_system.domain.model.EventHall;
import com.example.online_ticketing_system.domain.repository.EventHallRepository;
import com.example.online_ticketing_system.domain.service.EventHallService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EventHallServiceImpl implements EventHallService {

    private final EventHallRepository eventHallRepository;
    private final EventHallMapper eventHallMapper;

    @Autowired
    public EventHallServiceImpl(EventHallRepository eventHallRepository, EventHallMapper eventHallMapper) {
        this.eventHallRepository = eventHallRepository;
        this.eventHallMapper = eventHallMapper;
    }

    @Override
    public EventHallResponseDTO getEventHallById(Long id) {
        return eventHallRepository.findById(id)
                .map(eventHallMapper::toResponseDTO).orElse(null);
    }

    @Override
    public EventHallResponseDTO createEventHall(EventHallCreateDTO eventHallCreateDTO) {
        return eventHallMapper.toResponseDTO(
                eventHallRepository.save(
                        eventHallMapper.toEntity(eventHallCreateDTO)
                )
        );
    }

    @Override
    public List<EventHallResponseDTO> getAllEventHall() {
        return eventHallRepository.findAll().stream().map(eventHallMapper::toResponseDTO).collect(Collectors.toList());
    }

    @Override
    public EventHallResponseDTO findByName(String name) {
        return Optional.ofNullable(eventHallRepository.findByName(name))
                .map(eventHallMapper::toResponseDTO)
                .orElse(null);
    }

    @Override
    public EventHallResponseDTO update(Long id, EventHallUpdateDTO dto) {
        EventHall eventHall = eventHallRepository.findById(id).orElse(null);
        return eventHallMapper.toResponseDTO(eventHallRepository.save(eventHallMapper.updateEntityFromDTO(dto,eventHall)));
    }

    @Override
    public void delete(Long id) {
        EventHall eventHall = eventHallRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Event Hall not found!"));
        if (eventHall.getDeletedAt() != null) {
            throw new AlreadyDeletedException("Event Hall already deleted");
        }
        eventHallRepository.delete(eventHall.getId());
    }
}

package com.example.online_ticketing_system.application.service.impl;

import com.example.online_ticketing_system.application.dto.event.event_category.EventCategoryCreateDTO;
import com.example.online_ticketing_system.application.dto.event.event_category.EventCategoryResponseDTO;
import com.example.online_ticketing_system.application.dto.event.event_category.EventCategoryUpdateDTO;
import com.example.online_ticketing_system.application.mapper.EventCategoryMapper;
import com.example.online_ticketing_system.domain.exception.AlreadyDeletedException;
import com.example.online_ticketing_system.domain.exception.ResourceNotFoundException;
import com.example.online_ticketing_system.domain.model.EventCategory;
import com.example.online_ticketing_system.domain.model.EventHall;
import com.example.online_ticketing_system.domain.repository.EventCategoryRepository;
import com.example.online_ticketing_system.domain.service.EventCategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventCategoryServiceImpl implements EventCategoryService {

    private final EventCategoryMapper eventCategoryMapper;
    private final EventCategoryRepository eventCategoryRepository;

    @Autowired
    public EventCategoryServiceImpl(EventCategoryMapper eventCategoryMapper, EventCategoryRepository eventCategoryRepository) {
        this.eventCategoryMapper = eventCategoryMapper;
        this.eventCategoryRepository = eventCategoryRepository;
    }

    @Override
    public EventCategory save(EventCategoryCreateDTO eventCategoryCreateDTO) {
        EventCategory eventCategory = eventCategoryMapper.toEntity(eventCategoryCreateDTO);
        return eventCategoryRepository.save(eventCategory);
    }

    @Override
    public EventCategory findById(Long id) {
        return eventCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<EventCategoryResponseDTO> findAll() {
        return eventCategoryRepository.findAll()
                .stream()
                .map(eventCategoryMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EventCategoryResponseDTO findByName(String name) {
        EventCategory eventCategory= eventCategoryRepository.findByName(name);
        return eventCategoryMapper.toResponseDTO(eventCategory);
    }

    @Override
    public EventCategoryResponseDTO update(Long id, EventCategoryUpdateDTO eventCategoryUpdateDTO) {
        EventCategory eventCategory = eventCategoryRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Event category not found"));
        return eventCategoryMapper.toResponseDTO(
                eventCategoryRepository.save(
                        eventCategoryMapper.updateEventCategoryFromDTO(eventCategoryUpdateDTO, eventCategory)
                )
        );
    }

    @Override
    public void delete(Long id) {
        EventCategory eventCategory = eventCategoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Event Category not found!"));
        if (eventCategory.getDeletedAt() != null) {
            throw new AlreadyDeletedException("Event Category already deleted");
        }
        eventCategoryRepository.delete(eventCategory.getId());
    }
}

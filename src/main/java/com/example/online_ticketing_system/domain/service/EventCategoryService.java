package com.example.online_ticketing_system.domain.service;

import com.example.online_ticketing_system.application.dto.event.event_category.EventCategoryCreateDTO;
import com.example.online_ticketing_system.application.dto.event.event_category.EventCategoryResponseDTO;
import com.example.online_ticketing_system.application.dto.event.event_category.EventCategoryUpdateDTO;
import com.example.online_ticketing_system.domain.model.EventCategory;

import java.util.List;

public interface EventCategoryService {
    EventCategory save(EventCategoryCreateDTO eventCategoryCreateDTO);
    EventCategory findById(Long id);
    List<EventCategoryResponseDTO> findAll();
    EventCategoryResponseDTO findByName(String name);
    EventCategoryResponseDTO update(Long id, EventCategoryUpdateDTO eventCategoryUpdateDTO);
    void delete(Long id);
}

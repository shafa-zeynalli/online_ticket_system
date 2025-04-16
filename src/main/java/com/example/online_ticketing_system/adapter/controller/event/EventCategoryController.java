package com.example.online_ticketing_system.adapter.controller.event;


import com.example.online_ticketing_system.application.dto.event.event_category.EventCategoryCreateDTO;
import com.example.online_ticketing_system.application.dto.event.event_category.EventCategoryResponseDTO;
import com.example.online_ticketing_system.application.dto.event.event_category.EventCategoryUpdateDTO;
import com.example.online_ticketing_system.application.dto.event.event_hall.EventHallResponseDTO;
import com.example.online_ticketing_system.application.mapper.EventCategoryMapper;
import com.example.online_ticketing_system.domain.repository.EventCategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/event-category")
public class EventCategoryController {

    private final EventCategoryMapper eventCategoryMapper;

    private final EventCategoryRepository eventCategoryRepository;

    public EventCategoryController(EventCategoryMapper eventCategoryMapper, EventCategoryRepository eventCategoryRepository) {
        this.eventCategoryMapper = eventCategoryMapper;
        this.eventCategoryRepository = eventCategoryRepository;
    }

    @GetMapping
    public List<EventCategoryResponseDTO> getAll() {
        return eventCategoryRepository.findAll().stream().map(eventCategoryMapper::toResponseDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EventCategoryResponseDTO findById(@PathVariable Long id) {
        return eventCategoryRepository.findById(id).map(eventCategoryMapper::toResponseDTO).orElse(null);
    }

    @PostMapping
    public EventCategoryResponseDTO create(@RequestBody EventCategoryCreateDTO eventCategoryCreateDTO) {
        return eventCategoryMapper.toResponseDTO(
                eventCategoryRepository.save(
                    eventCategoryMapper.toEntity(eventCategoryCreateDTO)
                )
            );
    }


}

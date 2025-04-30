package com.example.online_ticketing_system.presentation.controller.event;


import com.example.online_ticketing_system.application.dto.event.event_category.EventCategoryCreateDTO;
import com.example.online_ticketing_system.application.dto.event.event_category.EventCategoryResponseDTO;
import com.example.online_ticketing_system.application.dto.event.event_category.EventCategoryUpdateDTO;
import com.example.online_ticketing_system.application.mapper.EventCategoryMapper;
import com.example.online_ticketing_system.domain.repository.EventCategoryRepository;
import com.example.online_ticketing_system.domain.service.EventCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/event-category")
public class EventCategoryController {

    private final EventCategoryMapper eventCategoryMapper;

    private final EventCategoryService eventCategoryService;

    public EventCategoryController(EventCategoryMapper eventCategoryMapper, EventCategoryService eventCategoryRepository) {
        this.eventCategoryMapper = eventCategoryMapper;
        this.eventCategoryService = eventCategoryRepository;
    }

    @GetMapping
    public List<EventCategoryResponseDTO> getAll() {
        return eventCategoryService.findAll();
    }

    @GetMapping("/{id}")
    public EventCategoryResponseDTO findById(@PathVariable Long id) {
        return Optional.ofNullable(eventCategoryService.findById(id)).map(eventCategoryMapper::toResponseDTO).orElse(null);
    }

    @PostMapping
    public EventCategoryResponseDTO create(@RequestBody EventCategoryCreateDTO eventCategoryCreateDTO) {
        return eventCategoryMapper.toResponseDTO(
                eventCategoryService.save(eventCategoryCreateDTO)
            );
    }
    @PutMapping("/{id}")
    public EventCategoryResponseDTO update(@PathVariable Long id, @RequestBody EventCategoryUpdateDTO eventCategoryUpdateDTO) {
        return eventCategoryService.update(id, eventCategoryUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }


}

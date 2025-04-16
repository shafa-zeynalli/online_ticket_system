package com.example.online_ticketing_system.application.mapper;


import com.example.online_ticketing_system.application.dto.event.event_category.EventCategoryCreateDTO;
import com.example.online_ticketing_system.application.dto.event.event_category.EventCategoryResponseDTO;
import com.example.online_ticketing_system.domain.model.EventCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventCategoryMapper {

    EventCategoryResponseDTO toResponseDTO(EventCategory entity);
    EventCategory toEntity(EventCategoryCreateDTO dto);
}

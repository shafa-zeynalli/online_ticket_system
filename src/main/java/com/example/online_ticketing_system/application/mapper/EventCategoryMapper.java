package com.example.online_ticketing_system.application.mapper;


import com.example.online_ticketing_system.application.dto.event.event_category.EventCategoryCreateDTO;
import com.example.online_ticketing_system.application.dto.event.event_category.EventCategoryResponseDTO;
import com.example.online_ticketing_system.application.dto.event.event_category.EventCategoryUpdateDTO;
import com.example.online_ticketing_system.application.dto.user.UserUpdateDTO;
import com.example.online_ticketing_system.domain.model.EventCategory;
import com.example.online_ticketing_system.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EventCategoryMapper {

    EventCategoryResponseDTO toResponseDTO(EventCategory entity);
    EventCategory toEntity(EventCategoryCreateDTO dto);

    EventCategory updateEventCategoryFromDTO(EventCategoryUpdateDTO dto, @MappingTarget EventCategory eventCategory);

}

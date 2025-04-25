package com.example.online_ticketing_system.application.mapper;

import com.example.online_ticketing_system.application.dto.event.event_category.EventCategoryCreateDTO;
import com.example.online_ticketing_system.application.dto.event.event_category.EventCategoryResponseDTO;
import com.example.online_ticketing_system.application.dto.seat_lock.SeatLockCreateDTO;
import com.example.online_ticketing_system.application.dto.seat_lock.SeatLockResponseDTO;
import com.example.online_ticketing_system.domain.model.EventCategory;
import com.example.online_ticketing_system.domain.model.SeatLock;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeatLockMapper {
    SeatLockResponseDTO toResponseDTO(SeatLock entity);
    SeatLock toEntity(SeatLockCreateDTO dto);

}

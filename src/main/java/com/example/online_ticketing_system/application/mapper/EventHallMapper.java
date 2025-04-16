package com.example.online_ticketing_system.application.mapper;

import com.example.online_ticketing_system.application.dto.event.event_hall.EventHallCreateDTO;
import com.example.online_ticketing_system.application.dto.event.event_hall.EventHallResponseDTO;
import com.example.online_ticketing_system.application.dto.event.event_hall.EventHallUpdateDTO;
import com.example.online_ticketing_system.domain.model.EventHall;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EventHallMapper {

    EventHallResponseDTO toResponseDTO(EventHall eventHall);
    EventHall toEntity(EventHallCreateDTO eventHallDTO);

    EventHall toUpdateEntity(EventHallUpdateDTO eventHallDTO);

    EventHall updateEntityFromDTO(EventHallUpdateDTO dto, @MappingTarget EventHall eventHall);


}

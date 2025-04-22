package com.example.online_ticketing_system.application.mapper;


import com.example.online_ticketing_system.application.dto.event.EventCreateDTO;
import com.example.online_ticketing_system.application.dto.event.EventResponseDTO;
import com.example.online_ticketing_system.application.dto.event.EventUpdateDTO;
import com.example.online_ticketing_system.domain.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(source = "eventCategory", target = "eventCategory")
    @Mapping(source = "eventHall", target = "eventHall")
    EventResponseDTO toResponseDTO(Event event);

    Event toEntity(EventCreateDTO eventDTO);

    void updateEntityFromDTO(EventUpdateDTO dto, @MappingTarget Event event);
    //bu metod MapStruct kitabxanasının bir hissəsidir və məqsədi mövcud bir entitiy-ni DTO məlumatları ilə update
    // etməkdir (yeni obyekt yaratmadan).

//    @MappingTarget nədir?
//    Bu annotasiya MapStruct-a deyir ki, "bu obyekt yenidən yaradılmasın, bu mövcud obyektin içindəki dəyərləri dəyiş".

}

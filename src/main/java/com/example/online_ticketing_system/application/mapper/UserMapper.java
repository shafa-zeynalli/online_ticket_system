package com.example.online_ticketing_system.application.mapper;


import com.example.online_ticketing_system.application.dto.event.EventUpdateDTO;
import com.example.online_ticketing_system.application.dto.user.UserCreateDTO;
import com.example.online_ticketing_system.application.dto.user.UserResponseDTO;
import com.example.online_ticketing_system.application.dto.user.UserUpdateDTO;
import com.example.online_ticketing_system.domain.model.Event;
import com.example.online_ticketing_system.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDTO toDto(User user);
    User toEntity(UserCreateDTO userCreateDTO);

    void updateUserFromDTO(UserUpdateDTO dto, @MappingTarget User event);

}

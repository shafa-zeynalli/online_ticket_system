package com.example.online_ticketing_system.application.dto.user;

import com.example.online_ticketing_system.domain.enums.Role;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private String fullName;
    private String address;
    private Role role;
}

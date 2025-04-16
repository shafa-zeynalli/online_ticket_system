package com.example.online_ticketing_system.application.dto.event.event_category;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventCategoryResponseDTO {
    private Long id;
    private String name;
}

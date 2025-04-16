package com.example.online_ticketing_system.application.dto.event;


import com.example.online_ticketing_system.domain.enums.EventStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventResponseDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String location;
    private String imageUrl;
    private Integer capacity;
    private String eventCategoryName;
    private String eventHallName;
    private EventStatus status;
}

package com.example.online_ticketing_system.application.dto.event.event_hall;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventHallUpdateDTO {
    private String name;
    private String location;
    private Integer capacity;
}

package com.example.online_ticketing_system.application.dto;

import com.example.online_ticketing_system.application.dto.event.EventCreateDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public class CouponDTO {
    private String name;
    private String code;
    private BigDecimal discount;
    private LocalDateTime validUntil;

    private EventCreateDTO event;
    private Boolean isActive;
}

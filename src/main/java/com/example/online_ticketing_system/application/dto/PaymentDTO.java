package com.example.online_ticketing_system.application.dto;

import com.example.online_ticketing_system.domain.enums.PaymentMethod;
import com.example.online_ticketing_system.domain.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentDTO {
    private BigDecimal amount;
    private LocalDateTime paidAt;

    private TicketDTO ticket;
    private PaymentMethod method;
    private PaymentStatus status;
 }

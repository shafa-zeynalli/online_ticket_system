package com.example.online_ticketing_system.application.dto.payment;

import com.example.online_ticketing_system.domain.enums.PaymentMethod;
import com.example.online_ticketing_system.domain.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentCreateDTO {
    private BigDecimal amount;
    private LocalDateTime paidAt;

    private Long ticketId;
    private PaymentMethod method;
    private PaymentStatus status;
}

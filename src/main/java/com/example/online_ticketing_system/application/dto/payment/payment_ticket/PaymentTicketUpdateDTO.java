package com.example.online_ticketing_system.application.dto.payment.payment_ticket;

import com.example.online_ticketing_system.domain.enums.PaymentTicketStatus;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentTicketUpdateDTO {
    private Long paymentId;
    private Long ticketId;
    private BigDecimal amount;
    private PaymentTicketStatus status;
}

package com.example.online_ticketing_system.application.dto.payment.payment_ticket;

import com.example.online_ticketing_system.application.dto.payment.PaymentResponseDTO;
import com.example.online_ticketing_system.application.dto.ticket.TicketResponseDTO;
import com.example.online_ticketing_system.domain.enums.PaymentTicketStatus;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentTicketResponseDTO {
    private Long id;
//    private PaymentResponseDTO payment;
    private TicketResponseDTO ticket;
    private BigDecimal amount;
    private PaymentTicketStatus status;
}

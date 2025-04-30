package com.example.online_ticketing_system.application.dto.payment;

import com.example.online_ticketing_system.application.dto.payment.payment_ticket.PaymentTicketCreateDTO;
import com.example.online_ticketing_system.domain.enums.PaymentMethod;
import com.example.online_ticketing_system.domain.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentCreateDTO {
    private BigDecimal totalAmount;
    private LocalDateTime paidAt;
    private PaymentMethod method;
    private PaymentStatus status;

    private List<PaymentTicketCreateDTO> paymentTickets;
}

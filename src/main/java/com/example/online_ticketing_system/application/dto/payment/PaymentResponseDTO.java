package com.example.online_ticketing_system.application.dto.payment;

import com.example.online_ticketing_system.application.dto.payment.payment_ticket.PaymentTicketResponseDTO;
import com.example.online_ticketing_system.application.dto.ticket.TicketResponseDTO;
import com.example.online_ticketing_system.application.dto.user.UserResponseDTO;
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
public class PaymentResponseDTO {
    private Long id;
    private BigDecimal totalAmount;
    private LocalDateTime paidAt;
    private PaymentMethod method;
    private PaymentStatus status;
    private String username;

    private List<PaymentTicketResponseDTO> paymentTickets;
}

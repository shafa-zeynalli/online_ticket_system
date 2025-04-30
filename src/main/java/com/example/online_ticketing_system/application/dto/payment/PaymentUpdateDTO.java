package com.example.online_ticketing_system.application.dto.payment;

import com.example.online_ticketing_system.application.dto.ticket.TicketResponseDTO;
import com.example.online_ticketing_system.domain.enums.PaymentMethod;
import com.example.online_ticketing_system.domain.enums.PaymentStatus;
import com.example.online_ticketing_system.domain.model.PaymentTicket;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentUpdateDTO {
    private BigDecimal totalAmount;
    private LocalDateTime paidAt;
    private PaymentMethod method;
    private PaymentStatus status;

    private List<PaymentTicket> tickets;
 }

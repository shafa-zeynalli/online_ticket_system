package com.example.online_ticketing_system.domain.service;

import com.example.online_ticketing_system.application.dto.payment.payment_ticket.PaymentTicketCreateDTO;
import com.example.online_ticketing_system.application.dto.payment.payment_ticket.PaymentTicketResponseDTO;
import com.example.online_ticketing_system.application.dto.payment.payment_ticket.PaymentTicketUpdateDTO;
import com.example.online_ticketing_system.domain.enums.PaymentTicketStatus;

import java.util.List;

public interface PaymentTicketService {
    PaymentTicketResponseDTO create(PaymentTicketCreateDTO dto);
    PaymentTicketResponseDTO update(Long id, PaymentTicketUpdateDTO dto);
    void delete(Long id);
    void deleteAllByPaymentId(Long paymentId);
    PaymentTicketResponseDTO findByTicketId(Long ticketId);
    List<PaymentTicketResponseDTO> findAllByPaymentId(Long paymentId);
    List<PaymentTicketResponseDTO> findAllByStatus(PaymentTicketStatus status);
}

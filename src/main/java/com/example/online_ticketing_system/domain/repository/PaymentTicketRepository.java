package com.example.online_ticketing_system.domain.repository;

import com.example.online_ticketing_system.application.dto.payment.PaymentCreateDTO;
import com.example.online_ticketing_system.domain.enums.PaymentTicketStatus;
import com.example.online_ticketing_system.domain.model.Payment;
import com.example.online_ticketing_system.domain.model.PaymentTicket;

import java.util.List;
import java.util.Optional;

public interface PaymentTicketRepository {
    Optional<PaymentTicket> findById(Long id);
    List<PaymentTicket> findAllByPaymentId(Long paymentId);
    PaymentTicket save(PaymentTicket paymentTicket);
    Optional<PaymentTicket> findByTicketId(Long ticketId);
    List<PaymentTicket> findAllByStatus(PaymentTicketStatus status);
    void deleteAll(List<PaymentTicket> paymentTickets);
    void delete(PaymentTicket paymentTicket);

}

package com.example.online_ticketing_system.infrastructure.persistence.repository;

import com.example.online_ticketing_system.domain.enums.PaymentTicketStatus;
import com.example.online_ticketing_system.domain.model.PaymentTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentTicketJpaRepository extends JpaRepository<PaymentTicket, Long> {
    List<PaymentTicket> findAllByPaymentId(Long paymentId);
    Optional<PaymentTicket> findByTicketId(Long ticketId);
    List<PaymentTicket> findAllByStatus(PaymentTicketStatus status);
}

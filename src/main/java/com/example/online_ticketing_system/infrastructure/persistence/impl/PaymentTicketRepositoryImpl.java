package com.example.online_ticketing_system.infrastructure.persistence.impl;

import com.example.online_ticketing_system.domain.enums.PaymentTicketStatus;
import com.example.online_ticketing_system.domain.exception.ResourceNotFoundException;
import com.example.online_ticketing_system.domain.model.PaymentTicket;
import com.example.online_ticketing_system.domain.repository.PaymentTicketRepository;
import com.example.online_ticketing_system.infrastructure.persistence.repository.PaymentTicketJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentTicketRepositoryImpl implements PaymentTicketRepository {

    private final PaymentTicketJpaRepository jpaRepository;

    @Override
    public Optional<PaymentTicket> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<PaymentTicket> findAllByPaymentId(Long paymentId) {
        return jpaRepository.findAllByPaymentId(paymentId);
    }

    @Override
    public PaymentTicket save(PaymentTicket paymentTicket) {
        return jpaRepository.save(paymentTicket);
    }

    @Override
    public Optional<PaymentTicket> findByTicketId(Long ticketId) {
        return Optional.ofNullable(jpaRepository.findByTicketId(ticketId))
                .orElseThrow(() -> new ResourceNotFoundException("PaymentTicket not found"));
    }

    @Override
    public List<PaymentTicket> findAllByStatus(PaymentTicketStatus status) {
        return jpaRepository.findAllByStatus(status);
    }

    @Override
    public void deleteAll(List<PaymentTicket> paymentTickets) {
         jpaRepository.deleteAll(paymentTickets);
    }

    @Override
    public void delete(PaymentTicket paymentTicket) {
        jpaRepository.delete(paymentTicket);
    }
}

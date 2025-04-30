package com.example.online_ticketing_system.domain.repository;

import com.example.online_ticketing_system.domain.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    Optional<Payment> findById(Long id);
    Payment save(Payment payment);
    void delete(Payment payment);
    List<Payment> getAll();
}

package com.example.online_ticketing_system.infrastructure.persistence.repository;

import com.example.online_ticketing_system.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  PaymentJpaRepository  extends JpaRepository<Payment, Long> {
}

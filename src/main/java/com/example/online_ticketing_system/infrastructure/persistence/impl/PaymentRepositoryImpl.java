package com.example.online_ticketing_system.infrastructure.persistence.impl;

import com.example.online_ticketing_system.domain.model.Payment;
import com.example.online_ticketing_system.domain.repository.PaymentRepository;
import com.example.online_ticketing_system.infrastructure.persistence.repository.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository jpaRepository;

    @Override
    public Optional<Payment> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Payment save(Payment payment) {
        return jpaRepository.save(payment);
    }

    @Override
    public void delete(Payment payment) {
        jpaRepository.delete(payment);
    }

    @Override
    public List<Payment> getAll() {
        return jpaRepository.findAll();
    }

}

package com.example.online_ticketing_system.domain.service;

import com.example.online_ticketing_system.application.dto.payment.PaymentCreateDTO;
import com.example.online_ticketing_system.application.dto.payment.PaymentResponseDTO;
import com.example.online_ticketing_system.application.dto.payment.PaymentUpdateDTO;
import com.example.online_ticketing_system.domain.model.User;

import java.util.List;

public interface PaymentService {
    PaymentResponseDTO create(PaymentCreateDTO dto);
//    PaymentResponseDTO update(Long id, PaymentUpdateDTO dto);
    PaymentResponseDTO getById(Long id);
    List<PaymentResponseDTO> getAllByPayments();
    void delete(Long id);
}

package com.example.online_ticketing_system.application.mapper;


import com.example.online_ticketing_system.application.dto.PaymentDTO;
import com.example.online_ticketing_system.domain.model.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentDTO toDto(Payment payment);
    Payment toEntity(PaymentDTO paymentDTO);
}

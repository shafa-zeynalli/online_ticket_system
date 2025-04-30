package com.example.online_ticketing_system.application.mapper;


import com.example.online_ticketing_system.application.dto.payment.PaymentCreateDTO;
import com.example.online_ticketing_system.application.dto.payment.PaymentResponseDTO;
import com.example.online_ticketing_system.application.dto.payment.PaymentUpdateDTO;
import com.example.online_ticketing_system.domain.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "paymentTickets", source = "paymentTickets")
    @Mapping(source = "user.fullName", target = "username")
    PaymentResponseDTO toDto(Payment payment);
    Payment toEntity(PaymentCreateDTO dto);

    void updateEntityFromDTO(PaymentUpdateDTO dto, @MappingTarget Payment payment);
}

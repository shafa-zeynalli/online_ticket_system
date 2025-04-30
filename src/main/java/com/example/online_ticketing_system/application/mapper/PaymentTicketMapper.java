package com.example.online_ticketing_system.application.mapper;


import com.example.online_ticketing_system.application.dto.payment.payment_ticket.PaymentTicketCreateDTO;
import com.example.online_ticketing_system.application.dto.payment.payment_ticket.PaymentTicketResponseDTO;
import com.example.online_ticketing_system.application.dto.payment.payment_ticket.PaymentTicketUpdateDTO;
import com.example.online_ticketing_system.domain.model.PaymentTicket;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PaymentTicketMapper {
//    @Mapping(source = "payment", target = "payment")
    PaymentTicketResponseDTO toResponseDto(PaymentTicket entity);

    PaymentTicketCreateDTO toCreateDto(PaymentTicket entity);
    PaymentTicket toEntity(PaymentTicketCreateDTO dto);

    void updateEntityFromDTO(PaymentTicketUpdateDTO dto, @MappingTarget PaymentTicket entity);
}

package com.example.online_ticketing_system.application.service.impl;

import com.example.online_ticketing_system.application.dto.payment.payment_ticket.PaymentTicketCreateDTO;
import com.example.online_ticketing_system.application.dto.payment.payment_ticket.PaymentTicketResponseDTO;
import com.example.online_ticketing_system.application.dto.payment.payment_ticket.PaymentTicketUpdateDTO;
import com.example.online_ticketing_system.application.mapper.PaymentTicketMapper;
import com.example.online_ticketing_system.domain.enums.PaymentTicketStatus;
import com.example.online_ticketing_system.domain.exception.ResourceNotFoundException;
import com.example.online_ticketing_system.domain.model.Payment;
import com.example.online_ticketing_system.domain.model.PaymentTicket;
import com.example.online_ticketing_system.domain.model.Ticket;
import com.example.online_ticketing_system.domain.repository.PaymentRepository;
import com.example.online_ticketing_system.domain.repository.PaymentTicketRepository;
import com.example.online_ticketing_system.domain.repository.TicketRepository;
import com.example.online_ticketing_system.domain.service.PaymentTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PaymentTicketServiceImpl implements PaymentTicketService {

    private final PaymentTicketMapper paymentTicketMapper;
    private final PaymentTicketRepository paymentTicketRepository;
    private final PaymentRepository paymentRepository;
    private final TicketRepository ticketRepository;

    @Override
    public PaymentTicketResponseDTO create(PaymentTicketCreateDTO dto) {
        Ticket ticket = ticketRepository.findById(dto.getTicketId())
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));

        Payment payment = paymentRepository.findById(dto.getPaymentId())
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));

        PaymentTicket entity = paymentTicketMapper.toEntity(dto);
        entity.setTicket(ticket);
        entity.setPayment(payment);
        entity.setAmount(dto.getAmount());
        entity.setStatus(PaymentTicketStatus.SUCCESS);
        PaymentTicket savePaymentTicket = paymentTicketRepository.save(entity);

        return paymentTicketMapper.toResponseDto(savePaymentTicket);
    }

    @Override
    public PaymentTicketResponseDTO update(Long id, PaymentTicketUpdateDTO dto) {
        PaymentTicket paymentTicket = paymentTicketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PaymentTicket not found"));

        paymentTicketMapper.updateEntityFromDTO(dto, paymentTicket);
        PaymentTicket updatedPaymentTicket = paymentTicketRepository.save(paymentTicket);
        return paymentTicketMapper.toResponseDto(updatedPaymentTicket);
    }

    @Override
    public void delete(Long id) {
        PaymentTicket paymentTicket = paymentTicketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PaymentTicket not found"));

        paymentTicketRepository.delete(paymentTicket);
    }

    @Override
    public void deleteAllByPaymentId(Long paymentId) {
        List<PaymentTicket> paymentTickets = paymentTicketRepository.findAllByPaymentId(paymentId);
        paymentTicketRepository.deleteAll(paymentTickets);
    }


    @Override
    public PaymentTicketResponseDTO findByTicketId(Long ticketId) {
        PaymentTicket paymentTicket = paymentTicketRepository.findByTicketId(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("PaymentTicket not found for the given Ticket ID"));

        return paymentTicketMapper.toResponseDto(paymentTicket);
    }


    @Override
    public List<PaymentTicketResponseDTO> findAllByPaymentId(Long paymentId) {
        List<PaymentTicket> paymentTickets = paymentTicketRepository.findAllByPaymentId(paymentId);

        return paymentTickets.stream()
                .map(paymentTicketMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentTicketResponseDTO> findAllByStatus(PaymentTicketStatus status) {
        List<PaymentTicket> paymentTickets = paymentTicketRepository.findAllByStatus(status);

        return paymentTickets.stream()
                .map(paymentTicketMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}

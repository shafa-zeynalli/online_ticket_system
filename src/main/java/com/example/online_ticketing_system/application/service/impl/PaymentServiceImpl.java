package com.example.online_ticketing_system.application.service.impl;

import com.example.online_ticketing_system.application.dto.payment.PaymentCreateDTO;
import com.example.online_ticketing_system.application.dto.payment.PaymentResponseDTO;
import com.example.online_ticketing_system.application.dto.payment.PaymentUpdateDTO;
import com.example.online_ticketing_system.application.dto.payment.payment_ticket.PaymentTicketCreateDTO;
import com.example.online_ticketing_system.application.mapper.PaymentMapper;
import com.example.online_ticketing_system.application.mapper.PaymentTicketMapper;
import com.example.online_ticketing_system.application.mapper.TicketMapper;
import com.example.online_ticketing_system.domain.enums.PaymentStatus;
import com.example.online_ticketing_system.domain.enums.TicketStatus;
import com.example.online_ticketing_system.domain.exception.AlreadyDeletedException;
import com.example.online_ticketing_system.domain.exception.ResourceNotFoundException;
import com.example.online_ticketing_system.domain.model.Payment;
import com.example.online_ticketing_system.domain.model.PaymentTicket;
import com.example.online_ticketing_system.domain.model.Ticket;
import com.example.online_ticketing_system.domain.model.User;
import com.example.online_ticketing_system.domain.repository.PaymentRepository;
import com.example.online_ticketing_system.domain.repository.TicketRepository;
import com.example.online_ticketing_system.domain.repository.UserRepository;
import com.example.online_ticketing_system.domain.service.PaymentService;
import com.example.online_ticketing_system.domain.service.PaymentTicketService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final TicketRepository ticketRepository;
    private final PaymentTicketService paymentTicketService;
    private final PaymentTicketMapper paymentTicketMapper;
    private final UserRepository userRepository;

    private final PaymentMapper paymentMapper;
    private final TicketMapper ticketMapper;

    @Override
    public PaymentResponseDTO create(PaymentCreateDTO dto) {
        Payment payment = paymentMapper.toEntity(dto);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new ResourceNotFoundException("User not found!"));
        payment.setUser(user);
        payment.setPaidAt(LocalDateTime.now());
        payment.setStatus(PaymentStatus.PENDING);

        List<Long> ticketIds = dto.getPaymentTickets().stream()
                                    .map(PaymentTicketCreateDTO::getTicketId).toList();
        List<Ticket> foundTickets = ticketRepository.findAllById(ticketIds);

        if (foundTickets.size() != ticketIds.size()) {
            throw new ResourceNotFoundException("Some tickets not found!");
        }

        Payment savePayment = paymentRepository.save(payment);
            for (Ticket ticket : foundTickets) {
                ticket.setStatus(TicketStatus.PAID);
                ticketRepository.save(ticket);

                PaymentTicketCreateDTO ptDto = dto.getPaymentTickets()
                                                        .stream()
                                                        .filter(pt -> pt.getTicketId().equals(ticket.getId()))
                                                        .findFirst()
                                                        .orElseThrow();
                ptDto.setPaymentId(savePayment.getId());
                paymentTicketService.create(ptDto);
            }
        savePayment.setStatus(PaymentStatus.SUCCESS);
        paymentRepository.save(savePayment);
        return paymentMapper.toDto(savePayment);
    }

//    @Override
//    public PaymentResponseDTO update(Long id, PaymentUpdateDTO dto) {
//        Payment payment = paymentRepository.findById(id)
//                .orElseThrow(()->new ResourceNotFoundException("Payment not found"));
//        paymentMapper.updateEntityFromDTO(dto, payment);
//        Payment savePayment = paymentRepository.save(payment);
//
//        if (dto.getTickets() != null && !dto.getTickets().isEmpty()) {
//            paymentTicketService.deleteAllByPaymentId(payment.getId());
//            for (PaymentTicket ticket : dto.getTickets()) {
//                Ticket linkedTicket = ticketRepository.findById(ticket.getTicket().getId())
//                        .orElseThrow(() -> new ResourceNotFoundException("Ticket not found: ID = " + ticket.getTicket().getId()));
//                ticket.setPayment(payment);
//                if (linkedTicket != null) {
//                    linkedTicket.setStatus(TicketStatus.PAID);
//                    ticketRepository.save(linkedTicket);
//                }
//                paymentTicketService.create(paymentTicketMapper.toCreateDto(ticket));
//            }
//        }
//        return paymentMapper.toDto(savePayment);
//    }

    @Override
    public PaymentResponseDTO getById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        return paymentMapper.toDto(payment);
    }

    @Override
    public List<PaymentResponseDTO> getAllByPayments() {
        return  paymentRepository.getAll()
                .stream()
                .map(paymentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
        if (payment.getDeletedAt() != null)
            throw new AlreadyDeletedException("Payment deleted at " + payment.getDeletedAt());
        paymentTicketService.deleteAllByPaymentId(payment.getId());
        paymentRepository.delete(payment);
    }
}

package com.example.online_ticketing_system.domain.model;


import com.example.online_ticketing_system.domain.enums.PaymentTicketStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "payment_tickets")
@SQLDelete(sql = "UPDATE payment_tickets SET deleted_at=NOW() WHERE id=?")
@Where(clause = "deleted_at IS NULL")
public class PaymentTicket extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @Column(nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentTicketStatus status;
}

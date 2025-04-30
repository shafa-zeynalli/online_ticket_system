package com.example.online_ticketing_system.domain.model;


import com.example.online_ticketing_system.domain.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "tickets")
@Entity
@NoArgsConstructor
@SQLDelete(sql = "UPDATE tickets SET deleted_at = NOW() WHERE id=?")
@Where(clause = "deleted_at IS NULL")
@Builder
@AllArgsConstructor
public class Ticket extends BaseEntity{

    @Column(name ="purchase_date")
    private LocalDateTime purchaseDate;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "seat_lock_id")
    private SeatLock seatLock;

    @ManyToOne
    @JoinColumn(name = "ticket_type_id", nullable = false)
    private EventTicketType ticketType;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status;

    @Column(name = "seat_number",nullable = false)
    private Integer seatNumber;

    private String qrCode;
}

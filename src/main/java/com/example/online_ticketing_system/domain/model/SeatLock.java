package com.example.online_ticketing_system.domain.model;

import com.example.online_ticketing_system.domain.enums.SeatLockStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Table(name = "seat_locks")
@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE seat_locks SET deleted_at = NOW() WHERE id=?")
@Where(clause = "deleted_at IS NULL")
public class SeatLock extends BaseEntity {

    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "locked_at", nullable = false)
    private LocalDateTime lockedAt;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Enumerated(EnumType.STRING)
    private SeatLockStatus status;

     private String reason;

    public boolean isPaymentCompleted() {
        return this.status == SeatLockStatus.PAID;
    }
}


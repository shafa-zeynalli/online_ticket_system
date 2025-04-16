package com.example.online_ticketing_system.domain.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "coupons")
@NoArgsConstructor
@SQLDelete(sql = "UPDATE coupons SET deleted_at=NOW() WHERE id=?")
@Where(clause = "deleted_at IS NULL")
public class Coupon extends BaseEntity{

    private String name;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private BigDecimal discount;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "valid_until", nullable = false)
    private LocalDateTime validUntil;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;  // Default olaraq aktivdir
}

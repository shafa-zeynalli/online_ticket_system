package com.example.online_ticketing_system.domain.model;


import com.example.online_ticketing_system.domain.enums.PaymentMethod;
import com.example.online_ticketing_system.domain.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "payments")
@NoArgsConstructor
@SQLDelete(sql = "UPDATE payments SET deleted_at=NOW() WHERE id=?")
@Where(clause = "deleted_at IS NULL")
public class Payment extends BaseEntity {

    @Column(nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod method;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;


//    layihede her hansi odeme inteqrasiyasi olmadigi ucun helelik bu column lazim deyil
//    @Column(name = "transaction_id", unique = true)
//    private String transactionId;

}

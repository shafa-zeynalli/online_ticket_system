package com.example.online_ticketing_system.domain.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created_at", updatable = false,columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at",columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at",columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime deletedAt;
}

//@MappedSuperclass nə edir? -- O class verilənlər bazasında ayrıca table yaratmır.
//Onun sahələri (fields) onu extend edən entity-lərin öz cədvəlinə daxil edilir.



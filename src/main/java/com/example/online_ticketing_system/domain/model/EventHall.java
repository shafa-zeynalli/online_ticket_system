package com.example.online_ticketing_system.domain.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
@Table(name = "event_halls")
@NoArgsConstructor
@SQLDelete(sql = "UPDATE event_halls SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class EventHall extends BaseEntity {

    private String name;
    private String location;
    private Integer capacity;
}

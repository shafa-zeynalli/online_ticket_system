package com.example.online_ticketing_system.domain.model;


import com.example.online_ticketing_system.domain.enums.TicketType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "event_ticket_types")
@SQLDelete(sql = "UPDATE event_ticket_types SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class EventTicketType extends BaseEntity {
    private String name;

    private String description;

    private TicketType ticketType;

    @ManyToOne
    private Event event;
}

//Change ticket type to event ticket type.
//You should add EventId to TicketType table. That being said you can add description column here.
//And you can add TicketType
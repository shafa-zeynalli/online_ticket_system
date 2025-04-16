package com.example.online_ticketing_system.application.state;


//Finite State Machine Logic

import com.example.online_ticketing_system.domain.enums.TicketStatus;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class DefaultTicketStatusTransitionValidator implements TicketStatusTransitionValidator {

    private static final Map<TicketStatus, Set<TicketStatus>> transitions = Map.of(
            TicketStatus.PENDING, Set.of(TicketStatus.PAID, TicketStatus.CANCELLED),
            TicketStatus.PAID, Set.of(TicketStatus.CANCELLED),
            TicketStatus.CANCELLED, Set.of()
    );

    @Override
    public boolean canTransition(TicketStatus from, TicketStatus to) {
        return transitions.getOrDefault(from, Set.of()).contains(to);
    }
}

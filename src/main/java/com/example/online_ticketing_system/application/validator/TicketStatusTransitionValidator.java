package com.example.online_ticketing_system.application.validator;

import com.example.online_ticketing_system.domain.enums.TicketStatus;

public interface TicketStatusTransitionValidator {
    boolean canTransition(TicketStatus from, TicketStatus to);
}

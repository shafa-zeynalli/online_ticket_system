package com.example.online_ticketing_system.domain.exception;

public class SeatAlreadyLockedException extends RuntimeException {
    public SeatAlreadyLockedException(String message) {super(message);}
}

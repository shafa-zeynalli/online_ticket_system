package com.example.online_ticketing_system.domain.exception;

public class AlreadyDeletedException extends RuntimeException {
    public AlreadyDeletedException(String message) {super(message);}
    public AlreadyDeletedException(String message, Throwable cause) {super(message, cause);}
}

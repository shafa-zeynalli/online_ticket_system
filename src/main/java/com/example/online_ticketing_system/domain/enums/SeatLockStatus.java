package com.example.online_ticketing_system.domain.enums;

public enum SeatLockStatus {
    LOCKED, // oturacaq tutulub amma payment gözlənilir
    PAID,   // ödəniş tamamlanıb
    EXPIRED // vaxtı keçib və boşaldılıb
}

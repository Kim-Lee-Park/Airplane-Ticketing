package com.sparta.airplane.ticketing.domain.reservation.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record ReservationNumber(
        @Column(name = "reservation_number", nullable = false)
        String reservationNumber
) {
    public ReservationNumber {
        if (reservationNumber == null || reservationNumber.isBlank()) {
            throw new IllegalArgumentException("예약 번호는 필수입니다.");
        }
    }

    public static ReservationNumber of(String reservationNumber) {
        return new ReservationNumber(reservationNumber);
    }
}

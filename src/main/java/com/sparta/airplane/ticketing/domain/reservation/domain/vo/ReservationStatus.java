package com.sparta.airplane.ticketing.domain.reservation.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationStatus {
    AWAIT_CONFIRMED("예약 확정 전"),
    CONFIRMED("예약 확정"),
    CANCELLED("예약 취소");

    private final String description;
}

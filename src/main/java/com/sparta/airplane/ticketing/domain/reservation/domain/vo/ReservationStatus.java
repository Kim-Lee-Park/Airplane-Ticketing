package com.sparta.airplane.ticketing.domain.reservation.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 예약 상태 관련 Enum
 * - AWAIT_CONFIRMED : 예약 생성 후 결제 이전 상태
 * - CONFIRMED : 결제가 완료되어 예약이 확정된 상태
 * - CANCELLED : 결제 환불 후 예약이 취소된 상태
 */
@Getter
@AllArgsConstructor
public enum ReservationStatus {
    AWAIT_CONFIRMED("예약 확정 전"),
    CONFIRMED("예약 확정"),
    CANCELLED("예약 취소");

    private final String description;
}

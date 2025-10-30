package com.sparta.airplane.ticketing.domain.reservation.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationStatus {
    // 예약 생성인데 뭔가 CREATE은 이상하고.. PENDING으로 우선 적기는 했는데 논의가 필요할 것 같습니다
    PENDING("예약 생성"),
    CONFIRMED("예약 확정"),
    CANCELLED("예약 취소");

    private final String description;
}

package com.sparta.airplane.ticketing.domain.ticketing.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 좌석 상태 값 객체 (Enum)
 */
@Getter
@AllArgsConstructor
public enum SeatStatus {
    BEFORE_ASSIGN("배정 전"),
    AFTER_ASSIGN("배정 후");

    private final String description;
}
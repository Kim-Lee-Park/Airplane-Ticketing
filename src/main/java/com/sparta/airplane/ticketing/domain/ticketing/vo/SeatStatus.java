package com.sparta.airplane.ticketing.domain.ticketing.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SeatStatus {
    BEFORE_ASSIGN("배정 전"),
    AFTER_ASSIGN("배정 후");

    private final String description;
}
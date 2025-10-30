package com.sparta.airplane.ticketing.domain.ticketing.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FlightStatus {
    SCHEDULED("예정"),
    DELAYED("지연"),
    DEPARTED("출발"),
    ARRIVED("도착"),
    CANCELLED("취소");

    private final String description;
}
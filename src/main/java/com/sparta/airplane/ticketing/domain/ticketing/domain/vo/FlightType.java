package com.sparta.airplane.ticketing.domain.ticketing.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 항공편 타입 값 객체 (Enum)
 */
@Getter
@AllArgsConstructor
public enum FlightType {
    ONE_WAY("편도"),
    ROUND_TRIP("왕복");

    private final String description;
}
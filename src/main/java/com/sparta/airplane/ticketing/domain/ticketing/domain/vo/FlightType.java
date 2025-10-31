package com.sparta.airplane.ticketing.domain.ticketing.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FlightType {
    ONE_WAY("편도"),
    ROUND_TRIP("왕복");

    private final String description;
}
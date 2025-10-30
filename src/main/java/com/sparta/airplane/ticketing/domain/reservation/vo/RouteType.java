package com.sparta.airplane.ticketing.domain.reservation.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RouteType {
    ONE_WAY("편도"),
    ROUND_TRIP("왕복");

    private final String description;
}

package com.sparta.airplane.ticketing.domain.payment.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MethodType {

    CARD("카드"),
    MILEAGE("마일리지");

    private final String type;
}

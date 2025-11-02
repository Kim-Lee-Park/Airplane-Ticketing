package com.sparta.airplane.ticketing.domain.payment.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 결제 수단 타입 값 객체 (Enum)
 */
@Getter
@AllArgsConstructor
public enum MethodType {

    CARD("카드"),
    MILEAGE("마일리지");

    private final String type;
}

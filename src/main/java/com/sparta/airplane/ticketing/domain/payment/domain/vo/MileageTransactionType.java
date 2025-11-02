package com.sparta.airplane.ticketing.domain.payment.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 마일리지 거래 유형 값 객체 (Enum)
 */
@Getter
@AllArgsConstructor
public enum MileageTransactionType {

    EARN("적립"),
    USE("사용");

    private final String description;
}

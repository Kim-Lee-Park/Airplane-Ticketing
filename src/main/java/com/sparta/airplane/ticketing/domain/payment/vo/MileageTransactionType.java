package com.sparta.airplane.ticketing.domain.payment.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MileageTransactionType {

    EARN("적립"),
    USE("사용");

    private final String description;
}

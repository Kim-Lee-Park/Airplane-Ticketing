package com.sparta.airplane.ticketing.domain.payment.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CardCompany {
    KB("KB국민카드"),
    HYUNDAI("현대카드"),
    SHINHAN("신한카드"),
    SAMSUNG("삼성카드"),
    LOTTE("롯데카드"),
    HANA("하나카드"),
    WOORI("우리카드"),
    CITI("씨티카드"),
    JEJU("제주카드");

    private final String companyName;
}

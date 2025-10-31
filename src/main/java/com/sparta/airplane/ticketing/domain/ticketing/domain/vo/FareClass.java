package com.sparta.airplane.ticketing.domain.ticketing.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FareClass {
    ECONOMY("이코노미석"),
    BUSINESS("비즈니스석"),
    FIRST("퍼스트석");

    private final String description;
}
package com.sparta.airplane.ticketing.domain.reservation.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 승객 성별 관련 Enum
 */
@Getter
@AllArgsConstructor
public enum Gender {
    MALE("남성"),
    FEMALE("여성");

    private final String description;
}

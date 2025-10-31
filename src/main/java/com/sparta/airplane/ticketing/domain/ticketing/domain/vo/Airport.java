package com.sparta.airplane.ticketing.domain.ticketing.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 공항 값 객체
 */
@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Airport {

    private String airportCode;

    private Airport(String airportCode) {
        if (airportCode == null || airportCode.isBlank()) {
            throw new IllegalArgumentException("공항 코드는 필수입니다");
        }
        this.airportCode = airportCode;
    }

    public static Airport of(String airportCode) {
        return new Airport(airportCode);
    }
}
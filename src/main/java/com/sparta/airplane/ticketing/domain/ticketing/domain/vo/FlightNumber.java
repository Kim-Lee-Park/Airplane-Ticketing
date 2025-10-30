package com.sparta.airplane.ticketing.domain.ticketing.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 항공편 번호 값 객체
 * 예: KE001, OZ123
 */
@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FlightNumber {

    private String flightNumber;

    private FlightNumber(String flightNumber) {
        if (flightNumber == null || flightNumber.isBlank()) {
            throw new IllegalArgumentException("항공편 번호는 필수입니다");
        }
        this.flightNumber = flightNumber.toUpperCase();
    }

    public static FlightNumber of(String flightNumber) {
        return new FlightNumber(flightNumber);
    }

}
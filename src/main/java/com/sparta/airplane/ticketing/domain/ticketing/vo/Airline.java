package com.sparta.airplane.ticketing.domain.ticketing.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 항공사 값 객체
 */
@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Airline {

    private String airline;

    private Airline(String airline) {
        if (airline == null || airline.isBlank()) {
            throw new IllegalArgumentException("항공사는 필수입니다");
        }
        this.airline = airline;
    }

    public static Airline of(String airline) {
        return new Airline(airline);
    }

}
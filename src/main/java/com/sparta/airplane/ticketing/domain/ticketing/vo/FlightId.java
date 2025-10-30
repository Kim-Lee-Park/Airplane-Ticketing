package com.sparta.airplane.ticketing.domain.ticketing.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 항공편 ID 값 객체
 */
@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FlightId {

    private String flightId;

    private FlightId(String flightId) {
        if (flightId == null || flightId.isBlank()) {
            throw new IllegalArgumentException("항공편 ID는 필수입니다");
        }
        this.flightId = flightId;
    }

    public static FlightId of(String flightId) {
        return new FlightId(flightId);
    }

}
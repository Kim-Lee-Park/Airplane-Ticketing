package com.sparta.airplane.ticketing.domain.ticketing.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 수하물 코드 값 객체
 */
@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaggageCode {

    private String baggageCode;

    private BaggageCode(String baggageCode) {
        this.baggageCode = baggageCode;
    }

    public static BaggageCode of(String baggageCode) {
        if (baggageCode == null || baggageCode.isBlank()) {
            return null;
        }
        return new BaggageCode(baggageCode);
    }
}
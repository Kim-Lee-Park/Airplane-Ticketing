package com.sparta.airplane.ticketing.domain.ticketing.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 승객 이름 값 객체
 */
@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PassengerName {

    private String passengerName;

    private PassengerName(String passengerName) {
        if (passengerName == null || passengerName.isBlank()) {
            throw new IllegalArgumentException("승객 이름은 필수입니다");
        }
        this.passengerName = passengerName;
    }

    public static PassengerName of(String passengerName) {
        return new PassengerName(passengerName);
    }

}
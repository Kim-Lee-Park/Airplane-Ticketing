package com.sparta.airplane.ticketing.domain.ticketing.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 승객 수용 인원 값 객체
 */
@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PassengerCapacity {

    private Integer capacity;

    private PassengerCapacity(Integer capacity) {
        if (capacity == null || capacity <= 0) {
            throw new IllegalArgumentException("승객 수용 인원은 0보다 커야 합니다");
        }
        this.capacity = capacity;
    }

    public static PassengerCapacity of(Integer capacity) {
        return new PassengerCapacity(capacity);
    }

}
package com.sparta.airplane.ticketing.domain.ticketing.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 좌석 번호 값 객체
 * 예: 12A, 34F
 */
@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SeatNumber {

    private String seatNumber;

    private SeatNumber(String seatNumber) {
        if (seatNumber == null || seatNumber.isBlank()) {
            throw new IllegalArgumentException("좌석 번호는 필수입니다");
        }
        this.seatNumber = seatNumber.toUpperCase();
    }

    public static SeatNumber of(String seatNumber) {
        return new SeatNumber(seatNumber);
    }

}
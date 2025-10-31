package com.sparta.airplane.ticketing.domain.ticketing.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 탑승 게이트 값 객체
 * 예: A12, B5
 */
@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardingGate {

    private String boardingGate;

    private BoardingGate(String boardingGate) {
        if (boardingGate == null || boardingGate.isBlank()) {
            throw new IllegalArgumentException("탑승 게이트는 필수입니다");
        }
        this.boardingGate = boardingGate.toUpperCase();
    }

    public static BoardingGate of(String boardingGate) {
        return new BoardingGate(boardingGate);
    }

}
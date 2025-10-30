package com.sparta.airplane.ticketing.domain.ticketing.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 탑승권 ID 값 객체
 */
@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardingPassId {

    private Long id;

    private BoardingPassId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("유효하지 않은 탑승권 ID입니다");
        }
        this.id = id;
    }

    public static BoardingPassId of(Long id) {
        return new BoardingPassId(id);
    }

}
package com.sparta.airplane.ticketing.domain.payment.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationId {

    private Long id;

    private ReservationId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("유효하지 않은 예약 ID입니다");
        }
        this.id = id;
    }

    public static ReservationId of(Long id) {
        return new ReservationId(id);
    }
}

package com.sparta.airplane.ticketing.domain.reservation.domain.vo;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.List;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 예약 총 금액 관련 값 객체
 * - 금액이라는 의미는 측정값으로 amount의 크기에 따라 동등성 비교
 * - 금액 관련 검증 로직을 생성자에 포함
 * - 관련 정책이 존재하지 않아 임의 값으로 우선 설정
 */
@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class TotalAmount {

    private final BigDecimal amount;

    public TotalAmount(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("금액은 null일 수 없습니다");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("금액은 0보다 커야 합니다");
        }

        this.amount = amount;
    }

    public static TotalAmount from(List<Passenger> passengers) {
        return new TotalAmount(BigDecimal.valueOf(10000L));
    }
}

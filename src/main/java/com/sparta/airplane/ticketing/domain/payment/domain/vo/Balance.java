package com.sparta.airplane.ticketing.domain.payment.domain.vo;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 마일리지 잔액 값 객체
 * - 마일리지 지갑의 현재 잔액을 나타내는 측정값
 * - BigDecimal 타입으로 정확한 금액 계산 보장
 * - 양수 검증 로직 포함
 */
@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Balance {

    private BigDecimal amount;

    private Balance(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("금액은 null일 수 없습니다");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("금액은 0보다 커야 합니다");
        }

        this.amount = amount;
    }

    public static Balance of(BigDecimal amount) {
        return new Balance(amount);
    }
}

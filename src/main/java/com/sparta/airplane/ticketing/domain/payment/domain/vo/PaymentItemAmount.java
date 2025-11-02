package com.sparta.airplane.ticketing.domain.payment.domain.vo;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 결제 항목 금액 값 객체
 * - 개별 결제 수단(카드, 마일리지)의 결제 금액을 나타냄
 * - 양수 및 null 검증 로직 포함
 */
@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class PaymentItemAmount {

    private final BigDecimal amount;

    private PaymentItemAmount(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("금액은 null일 수 없습니다");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("금액은 0보다 커야 합니다");
        }

        this.amount = amount;
    }

    public static PaymentItemAmount of(BigDecimal amount) {
        return new PaymentItemAmount(amount);
    }
}

package com.sparta.airplane.ticketing.domain.payment.vo;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

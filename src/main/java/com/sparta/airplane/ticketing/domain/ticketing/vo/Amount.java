package com.sparta.airplane.ticketing.domain.ticketing.vo;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.math.BigDecimal;

/**
 * 금액 값 객체
 */
@Embeddable
@Getter
@EqualsAndHashCode
public class Amount {

    private final BigDecimal amount;

    protected Amount() {
        this.amount = BigDecimal.ZERO;
    }

    private Amount(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("금액은 null일 수 없습니다");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("금액은 음수일 수 없습니다");
        }
        this.amount = amount;
    }

    public static Amount of(BigDecimal amount) {
        return new Amount(amount);
    }

    public static Amount zero() {
        return new Amount(BigDecimal.ZERO);
    }

    public Amount add(Amount other) {
        return new Amount(this.amount.add(other.amount));
    }

    public Amount subtract(Amount other) {
        return new Amount(this.amount.subtract(other.amount));
    }

    public Amount multiply(double rate) {
        return new Amount(this.amount.multiply(BigDecimal.valueOf(rate)));
    }

    public boolean isPositive() {
        return this.amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isGreaterThan(Amount other) {
        return this.amount.compareTo(other.amount) > 0;
    }

}
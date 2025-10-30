package com.sparta.airplane.ticketing.domain.reservation.domain.vo;

import java.math.BigDecimal;

public record ChangeRule(boolean allowed, BigDecimal feePercent) {
    public static ChangeRule forbidden() {
        return new ChangeRule(false, BigDecimal.ZERO);
    }

    public static ChangeRule free() {
        return new ChangeRule(true, BigDecimal.ZERO);
    }

    public static ChangeRule allowed(BigDecimal feePercent) {
        return new ChangeRule(true, feePercent);
    }
}

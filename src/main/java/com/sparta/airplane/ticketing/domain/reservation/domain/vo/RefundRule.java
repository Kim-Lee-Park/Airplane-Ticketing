package com.sparta.airplane.ticketing.domain.reservation.domain.vo;

import java.math.BigDecimal;

public record RefundRule(boolean allowed, BigDecimal feePercent) {
    public static RefundRule forbidden() {
        return new RefundRule(false, BigDecimal.ZERO);
    }

    public static RefundRule free() {
        return new RefundRule(true, BigDecimal.ZERO);
    }

    public static RefundRule allowed(BigDecimal feePercent) {
        return new RefundRule(true, feePercent);
    }
}

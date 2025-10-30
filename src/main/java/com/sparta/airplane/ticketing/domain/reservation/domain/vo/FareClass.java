package com.sparta.airplane.ticketing.domain.reservation.domain.vo;

import java.math.BigDecimal;

public enum FareClass {
    PROMOTION(
            50,
            ChangeRule.forbidden(),
            RefundRule.forbidden()
    ),
    DISCOUNT(
            75,
            ChangeRule.allowed(BigDecimal.ZERO),
            RefundRule.allowed(BigDecimal.valueOf(50))
    ),
    NORMAL(
            100,
            ChangeRule.allowed(BigDecimal.ZERO),
            RefundRule.allowed(BigDecimal.valueOf(10))
    ),
    FLEX(
            150,
            ChangeRule.free(),
            RefundRule.free()
    );

    public final int mileagePercent;

    private final ChangeRule changeRule;

    private final RefundRule refundRule;

    FareClass(int mileagePercent, ChangeRule changeRule, RefundRule refundRule) {
        this.mileagePercent = mileagePercent;
        this.changeRule = changeRule;
        this.refundRule = refundRule;
    }
}

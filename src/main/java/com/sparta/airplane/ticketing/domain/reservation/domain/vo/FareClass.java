package com.sparta.airplane.ticketing.domain.reservation.domain.vo;

import java.math.BigDecimal;
import lombok.Getter;

/**
 * 운임 클래스 관련 Enum
 * - PROMOTION: 프로모션 (50% 마일리지, 변경/환불 불가)
 * - DISCOUNT: 할인 (75% 마일리지, 무료 변경, 50% 수수료 환불)
 * - NORMAL: 일반 (100% 마일리지, 무료 변경, 10% 수수료 환불)
 * - FLEX: 플렉스 (150% 마일리지, 무료 변경, 무료 환불)
 */
@Getter
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

package com.sparta.airplane.ticketing.domain.reservation.domain.vo;

import java.math.BigDecimal;

/**
 * 환불 정책 관련 값 객체
 * - forbidden : 환불 불가능
 * - free : 환불 가능, 수수료 없음
 * - allowed : 환불 가능, 수수료 존재
 */
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

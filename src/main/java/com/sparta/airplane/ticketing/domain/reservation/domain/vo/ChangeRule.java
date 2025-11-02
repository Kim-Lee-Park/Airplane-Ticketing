package com.sparta.airplane.ticketing.domain.reservation.domain.vo;

import java.math.BigDecimal;

/**
 * 예약 변경 정책 관련 값 객체
 * - forbidden : 예약 변경 불가능
 * - free : 예약 변경 가능, 수수료 없음
 * - allowed : 예약 변경 가능, 수수료 존재
 */
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

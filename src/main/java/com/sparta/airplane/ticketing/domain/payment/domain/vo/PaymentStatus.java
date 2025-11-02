package com.sparta.airplane.ticketing.domain.payment.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 결제 상태 값 객체 (Enum)
 */
@Getter
@AllArgsConstructor
public enum PaymentStatus {

    PENDING("결제 대기"),
    COMPLETED("결제 완료"),
    FAILED("결제 실패"),
    CANCELLED("결제 취소"),
    REFUNDED("환불 완료");

    private final String description;
}

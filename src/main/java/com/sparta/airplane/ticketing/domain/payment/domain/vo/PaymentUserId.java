package com.sparta.airplane.ticketing.domain.payment.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 결제 사용자 ID 값 객체
 * - 양수 및 null 검증 로직 포함
 */
@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentUserId {

    private Long id;

    private PaymentUserId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("유효하지 않은 사용자 ID입니다");
        }
        this.id = id;
    }

    public static PaymentUserId of(Long id) {
        return new PaymentUserId(id);
    }
}

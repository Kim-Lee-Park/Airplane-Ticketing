package com.sparta.airplane.ticketing.domain.payment.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerId {

    private Long id;

    private CustomerId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("유효하지 않은 고객 ID입니다");
        }
        this.id = id;
    }

    public static CustomerId of(Long id) {
        return new CustomerId(id);
    }
}

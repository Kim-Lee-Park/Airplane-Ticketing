package com.sparta.airplane.ticketing.domain.payment.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Balance {

    private Long amount;

    private Balance(Long amount) {
        if (amount == null || amount < 0) {
            throw new IllegalArgumentException("잔액은 음수일 수 없습니다");
        }
        this.amount = amount;
    }

    public static Balance of(Long amount) {
        return new Balance(amount);
    }
}

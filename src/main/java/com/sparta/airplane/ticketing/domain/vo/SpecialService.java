package com.sparta.airplane.ticketing.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED, force = true)
public class SpecialService {

    // 스폐셜 서비스, 부가 서비스, 수하물 정책 규정이 존재하는데 우선은 String으로 작성을 진행했습니다.
    private final String specialServiceCode;
    private final String additionalServiceCode;
    private final String baggageCode;
    private final boolean inFlightMeal;

    private SpecialService(String specialServiceCode, String additionalServiceCode, String baggageCode, boolean inFlightMeal) {
        // 비즈니스 검증 로직 추가
        this.specialServiceCode = specialServiceCode;
        this.additionalServiceCode = additionalServiceCode;
        this.baggageCode = baggageCode;
        this.inFlightMeal = inFlightMeal;
    }

    // 기내식을 선택하지 않았을 경우 false로 기본 설정
    public static SpecialService of(String specialServiceCode, String additionalServiceCode, String baggageCode) {
        return new SpecialService(specialServiceCode, additionalServiceCode, baggageCode, false);
    }

    public static SpecialService of(String specialServiceCode, String additionalServiceCode, String baggageCode, boolean inFlightMeal) {
        return new SpecialService(specialServiceCode, additionalServiceCode, baggageCode, inFlightMeal);
    }
}

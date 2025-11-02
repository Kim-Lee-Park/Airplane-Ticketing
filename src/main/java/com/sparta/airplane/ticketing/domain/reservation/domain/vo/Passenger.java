package com.sparta.airplane.ticketing.domain.reservation.domain.vo;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

/**
 * 예약된 승객 정보 관련 값 객체
 * - 승객별 운임 클래스, 특별 서비스 옵션 관리
 * - 이름, 생년월일, 운임 클래스 관련 검증 로직을 생성자에 포함
 */
@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED, force = true)
public class Passenger {
    @Comment("승객 이름")
    private final String name;

    @Comment("승객 성별")
    private final Gender gender;

    @Comment("생년월일")
    private final LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Comment("운임 클래스")
    private final FareClass fareClass;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "specialServiceCode", column = @Column(name = "special_service_code")),
        @AttributeOverride(name = "additionalServiceCode", column = @Column(name = "additional_service_code")),
        @AttributeOverride(name = "baggageCode", column = @Column(name = "baggage_code")),
        @AttributeOverride(name = "inFlightMeal", column = @Column(name = "in_flight_meal"))
    })
    private final SpecialService specialService;

    public Passenger(String name, Gender gender, LocalDate birthDate, FareClass fareClass, SpecialService specialService) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("승객 이름은 필수입니다");
        }

        if (birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("생년월일은 미래일 수 없습니다");
        }

        if (fareClass == null) {
            throw new IllegalArgumentException("운임 클래스는 필수입니다");
        }

        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.fareClass = fareClass;
        this.specialService = specialService;
    }
}

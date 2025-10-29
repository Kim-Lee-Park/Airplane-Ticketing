package com.sparta.airplane.ticketing.domain.vo;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED, force = true)
public class Passenger {
    private final String name;
    private final Gender gender;
    private final LocalDate birthDate;
    private final String fareClass; // 단순 좌석보다는 운임 클래스 정책에 관한 부분인데 우선은 그냥 String 으로 생성했습니다
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "specialServiceCode", column = @Column(name = "special_service_code")),
        @AttributeOverride(name = "additionalServiceCode", column = @Column(name = "additional_service_code")),
        @AttributeOverride(name = "baggageCode", column = @Column(name = "baggage_code")),
        @AttributeOverride(name = "inFlightMeal", column = @Column(name = "in_flight_meal"))
    })
    private final SpecialService specialService;

    private Passenger(String name, Gender gender, LocalDate birthDate, String fareClass, SpecialService specialService) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("승객 이름은 필수입니다");
        }

        if (birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("생년월일은 미래일 수 없습니다");
        }

        if (fareClass == null || fareClass.isBlank()) {
            throw new IllegalArgumentException("운임 클래스는 필수입니다");
        }

        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.fareClass = fareClass;
        this.specialService = specialService;
    }

    public static Passenger of(String name, Gender gender, LocalDate birthDate, String fareClass, SpecialService specialService) {
        return new Passenger(name, gender, birthDate, fareClass, specialService);
    }
}

package com.sparta.airplane.ticketing.domain.ticketing.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PassengerId {

    private Long id;

    private PassengerId(Long id){
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("유효하지 않은 승객 ID입니다");
        }
        this.id = id;
    }
    public static PassengerId of(Long id) {
        return new PassengerId(id);
    }
}

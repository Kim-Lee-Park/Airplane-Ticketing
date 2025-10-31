package com.sparta.airplane.ticketing.domain.ticketing.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
일단 튜터님 코드 그대로 따라서 Id 별로 한번 생성하겠습니다.
* 항공권 ID 값 객체
* */
@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaneTicketId {

    private Long id;

    private PlaneTicketId(Long id){
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("유효하지 않은 항공권 ID입니다");
        }
        this.id = id;
    }
    public static PlaneTicketId of(Long id) {
        return new PlaneTicketId(id);
    }
}

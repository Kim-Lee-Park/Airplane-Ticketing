package com.sparta.airplane.ticketing.domain.vo;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@EqualsAndHashCode
// 값 객체의 경우 불변 보장을 위해 필드를 final로 선언, JPA에서 리플렉션을 통해 객체를 생성할 수 있도록 기본 생성자 필요
// Protected 접근 제어자 + force 옵션을 통해 final 필드에 기본값 설정
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED, force = true)
public class RouteInfo {
    private final String from;
    private final String to;
    @Enumerated(EnumType.STRING)
    private final RouteType routeType;

    private RouteInfo(String from, String to, RouteType routeType) {
        if (from == null || from.isBlank()) {
            throw new IllegalArgumentException("출발지는 필수입니다");
        }
        if (to == null || to.isBlank()) {
            throw new IllegalArgumentException("도착지는 필수입니다");
        }
        if (from.equals(to)) {
            throw new IllegalArgumentException("출발지와 도착지는 같을 수 없습니다");
        }

        this.from = from;
        this.to = to;
        this.routeType = routeType;
    }

    // 별도의 표시를 하지 않았을 시에는 편도를 기본값으로 사용
    public static RouteInfo of(String from, String to) {
        return new RouteInfo(from, to, RouteType.ONE_WAY);
    }

    public static RouteInfo of(String from, String to, RouteType routeType) {
        return new RouteInfo(from, to, routeType);
    }
}

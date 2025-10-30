package com.sparta.airplane.ticketing.domain.reservation.vo;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED, force = true)
public class RouteInfo {
    @Comment("출발지")
    private final String from;

    @Comment("도착지")
    private final String to;

    @Comment("경로 정보 타입")
    @Enumerated(EnumType.STRING)
    private final RouteType routeType;

    public RouteInfo(String from, String to, RouteType routeType) {
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
    public RouteInfo(String from, String to) {
        this(from, to, RouteType.ONE_WAY);
    }
}

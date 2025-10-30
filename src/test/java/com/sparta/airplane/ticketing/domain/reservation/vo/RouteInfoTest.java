package com.sparta.airplane.ticketing.domain.reservation.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RouteInfoTest {

    @Test
    @DisplayName("출발지와 도착지는 필수입니다")
    void emptyFrom() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new RouteInfo("", "")
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> new RouteInfo(null, null)
        );
    }

    @Test
    @DisplayName("출발지와 도착지는 같을 수 없습니다")
    void sameValue() {
        String from = "같은값";
        assertThrows(
                IllegalArgumentException.class,
                () -> new RouteInfo(from, from)
        );
    }
}

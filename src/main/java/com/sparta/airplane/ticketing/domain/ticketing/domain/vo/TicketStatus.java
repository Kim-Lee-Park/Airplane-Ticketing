package com.sparta.airplane.ticketing.domain.ticketing.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TicketStatus {
    BEFORE_CHECKIN("체크인 전"),
    AFTER_CHECKIN("체크인 후");

    private final String description;
}
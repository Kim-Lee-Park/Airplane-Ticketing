package com.sparta.airplane.ticketing.domain.ticketing.domain.entity;

import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.FareClass;
import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.FlightId;
import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.PassengerId;
import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.SeatNumber;
import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.SeatStatus;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 좌석 Entity
 *
 * <역할>
 * - 항공편의 개별 좌석 정보 및 배정 상태를 관리
 * - 좌석 번호, 운임 클래스, 좌석 상태(예약 가능/배정됨 등), 배정된 승객 정보 포함
 *
 * <설계 이유>
 * - Entity로 설계한 이유:
 *   1. 식별자(ID)가 필요하며, 좌석 배정 상태가 변경되는 생명주기 존재
 *   2. 좌석은 항공편과 독립적으로 조회 및 관리 가능
 *
 * - Aggregate Root가 아닌 이유:
 *   좌석의 경우, 좌석 상태(LOCK 여부)가 관리되며, 추적될 필요가 있음
 */
@Entity
@Table(name = "seats")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "flightId", column = @Column(name = "flight_id"))
    private FlightId flightId;

    @Embedded
    @AttributeOverride(name = "passengerId", column = @Column(name = "passenger_id"))
    private PassengerId passengerId;

    @Embedded
    @AttributeOverride(name = "seatNumber", column = @Column(name = "seat_number", nullable = false))
    private SeatNumber seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "fareclass", nullable = false)
    private FareClass fareClass;

    @Enumerated(EnumType.STRING)
    @Column(name = "seatstatus", nullable = false)
    private SeatStatus seatStatus;

    @Column(name = "assign_at", nullable = false)
    private LocalDateTime assignAt;

}

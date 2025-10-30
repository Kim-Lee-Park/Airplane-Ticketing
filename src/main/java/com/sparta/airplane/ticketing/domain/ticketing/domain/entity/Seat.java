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

package com.sparta.airplane.ticketing.domain.ticketing.entity;

import com.sparta.airplane.ticketing.domain.ticketing.vo.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDateTime;

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

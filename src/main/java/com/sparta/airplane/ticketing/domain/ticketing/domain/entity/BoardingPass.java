package com.sparta.airplane.ticketing.domain.ticketing.domain.entity;

import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.Airline;
import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.Airport;
import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.BoardingGate;
import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.FareClass;
import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.FlightNumber;
import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.PassengerName;
import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.PlaneTicketId;
import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.SeatNumber;
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
import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
@Table(name = "boarding_pass")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardingPass extends AbstractAggregateRoot<BoardingPass> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "planeTicketId", column = @Column(name = "plane_ticket_id", nullable = false))
    private PlaneTicketId planeTicketId;

    @Embedded
    @AttributeOverride(name = "seatNumber", column = @Column(name = "seat_number", nullable = false))
    private SeatNumber seatNumber;

    @Embedded
    @AttributeOverride(name = "boardingGate", column = @Column(name = "boarding_gate", nullable = false))
    private BoardingGate boardingGate;

    @Column(name = "boarding_time", nullable = false)
    private LocalDateTime boardingTime;

    @Embedded
    @AttributeOverride(name = "passengerName", column = @Column(name = "passenger_name", nullable = false))
    private PassengerName passengerName;

    @Embedded
    @AttributeOverride(name = "airportCode", column = @Column(name = "departure_airport", nullable = false))
    private Airport from;

    @Embedded
    @AttributeOverride(name = "airportCode", column = @Column(name = "arrival_airport", nullable = false))
    private Airport to;

    @Embedded
    @AttributeOverride(name = "flightNumber", column = @Column(name = "flight_number", nullable = false))
    private FlightNumber flightNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "fareclass", nullable = false)
    private FareClass fareClass;

    @Embedded
    @AttributeOverride(name = "airline", column = @Column(name = "airline", nullable = false))
    private Airline airline;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;


    /**
     * 탑승권 생성 팩토리 메서드
     */
    public static BoardingPass create(
            PlaneTicketId planeTicketId,
            SeatNumber seatNumber,
            BoardingGate boardingGate,
            LocalDateTime boardingTime,
            PassengerName passengerName,
            Airport from,
            Airport to,
            FlightNumber flightNumber,
            FareClass fareClass,
            Airline airline) {

        BoardingPass boardingPass = new BoardingPass();
        boardingPass.planeTicketId = planeTicketId;
        boardingPass.seatNumber = seatNumber;
        boardingPass.boardingGate = boardingGate;
        boardingPass.boardingTime = boardingTime;
        boardingPass.passengerName = passengerName;
        boardingPass.from = from;
        boardingPass.to = to;
        boardingPass.flightNumber = flightNumber;
        boardingPass.fareClass = fareClass;
        boardingPass.airline = airline;
        boardingPass.createdAt = LocalDateTime.now();

        // 비즈니스 규칙 검증
        boardingPass.validate();

        return boardingPass;
    }

    private void validate() {
        if (boardingTime == null) {
            throw new IllegalArgumentException("탑승 시간은 필수입니다");
        }
        if (boardingTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("탑승 시간은 현재 시간 이후여야 합니다");
        }
        if (fareClass == null) {
            throw new IllegalArgumentException("운임 클래스는 필수입니다");
        }
    }

}
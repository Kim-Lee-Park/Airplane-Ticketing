package com.sparta.airplane.ticketing.domain.ticketing.domain.entity;

import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.Airline;
import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.Airport;
import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.FlightStatus;
import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.FlightType;
import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.PassengerCapacity;
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
@Table(name = "flights")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Flight extends AbstractAggregateRoot<Flight> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 출발,도착,지연 등 상태
    @Enumerated(EnumType.STRING)
    @Column(name = "flight_status", nullable = false)
    private FlightStatus flightStatus;

    @Embedded
    @AttributeOverride(name = "airportCode", column = @Column(name = "departure_airport", nullable = false))
    private Airport from;

    @Embedded
    @AttributeOverride(name = "airportCode", column = @Column(name = "arrival_airport", nullable = false))
    private Airport to;

    // 예상 출발 시간
    @Column(name = "etd", nullable = false)
    private LocalDateTime etd;

    // 예상 도착 시간
    @Column(name = "eta", nullable = false)
    private LocalDateTime eta;

    // 편도, 왕복
    @Enumerated(EnumType.STRING)
    @Column(name = "flight_type", nullable = false)
    private FlightType flightType;


    @Embedded
    @AttributeOverride(name = "capacity", column = @Column(name = "passenger_capacity", nullable = false))
    private PassengerCapacity passengerCapacity;

    // 경유지
    @Column(nullable = false)
    private String via;

    @Embedded
    @AttributeOverride(name = "airline", column = @Column(name = "airline", nullable = false))
    private Airline airline;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    /**
     * 항공편 생성 팩토리 메서드
     */
    public static Flight create(
            FlightStatus flightStatus,
            Airport from,
            Airport to,
            LocalDateTime etd,
            LocalDateTime eta,
            FlightType flightType,
            PassengerCapacity passengerCapacity,
            String via,
            Airline airline) {

        Flight flight = new Flight();
        flight.flightStatus = flightStatus;
        flight.from = from;
        flight.to = to;
        flight.etd = etd;
        flight.eta = eta;
        flight.flightType = flightType;
        flight.passengerCapacity = passengerCapacity;
        flight.via = via;
        flight.airline = airline;
        flight.createdAt = LocalDateTime.now();

        // 비즈니스 규칙 검증
        flight.validate();

        return flight;
    }

    private void validate() {
        if (from.getAirportCode().equals(to.getAirportCode())) {
            throw new IllegalArgumentException("출발지와 도착지는 같을 수 없습니다");
        }
        if (etd.isAfter(eta)) {
            throw new IllegalArgumentException("출발 시간은 도착 시간보다 빠를 수 없습니다");
        }
        if (via == null || via.isBlank()) {
            throw new IllegalArgumentException("경유지 정보는 필수입니다");
        }
    }

}
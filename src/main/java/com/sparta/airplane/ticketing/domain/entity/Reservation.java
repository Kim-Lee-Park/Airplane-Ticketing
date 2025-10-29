package com.sparta.airplane.ticketing.domain.entity;

import com.sparta.airplane.ticketing.domain.vo.Passenger;
import com.sparta.airplane.ticketing.domain.vo.ReservationStatus;
import com.sparta.airplane.ticketing.domain.vo.RouteInfo;
import com.sparta.airplane.ticketing.domain.vo.TotalAmount;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
@Table(name = "reservations")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends AbstractAggregateRoot<Reservation> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reservationNumber;
    private String airline;
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    // @AttributeOverride 옵션의 name 속성은 VO 클래스의 실제 필드명,
    // column 속성은 테이블 생성 시 사용할 컬럼명을 설정
    // ERD 설계에 따라 컬럼명은 변경될 수 있음
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "from", column = @Column(name = "departure_airport")),
        @AttributeOverride(name = "to", column = @Column(name = "arrival_airport")),
        @AttributeOverride(name = "routeType", column = @Column(name = "route_type"))
    })
    private RouteInfo routeInfo;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "total_amount"))
    private TotalAmount totalAmount;

    @ElementCollection
    @CollectionTable(
        name = "reservation_passengers",
        joinColumns = @JoinColumn(name = "reservation_id")
    )
    private List<Passenger> passengers;

    public static Reservation create(String airline, RouteInfo routeInfo, List<Passenger> passengers, TotalAmount totalAmount) {
        // 예약번호 생성 로직 필요
        String reservationNumber = "TEST-NUMBER";

        // 검증 로직 필요
        if (airline == null || airline.isBlank()) {
            throw new IllegalArgumentException("항공사는 필수입니다");
        }

        Reservation reservation = new Reservation();
        reservation.reservationNumber = reservationNumber;
        reservation.airline = airline;
        reservation.routeInfo = routeInfo;
        reservation.passengers = List.copyOf(passengers);
        reservation.totalAmount = totalAmount;
        reservation.reservationStatus = ReservationStatus.PENDING;

        return reservation;
    }
}

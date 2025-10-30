package com.sparta.airplane.ticketing.domain.reservation.entity;

import com.sparta.airplane.ticketing.domain.reservation.vo.*;
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
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;
import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
@Table(name = "reservations")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NaturalIdCache
public class Reservation extends AbstractAggregateRoot<Reservation> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Embedded
    private ReservationNumber reservationNumber;

    @Column(name = "aireline", nullable = false)
    @Comment("항공사")
    private String airline;

    @Enumerated(EnumType.STRING)
    @Comment("예약 상태")
    private ReservationStatus reservationStatus;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    @Comment("생성 일시")
    private LocalDateTime createdAt;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "from", column = @Column(name = "departure_airport", nullable = false)),
        @AttributeOverride(name = "to", column = @Column(name = "arrival_airport", nullable = false)),
        @AttributeOverride(name = "routeType", column = @Column(name = "route_type", nullable = false)),
    })
    @Comment("항공편 경로 정보")
    private RouteInfo routeInfo;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "total_amount"))
    @Comment("총 가격")
    private TotalAmount totalAmount;

    @ElementCollection
    @CollectionTable(
        name = "reservation_passengers",
        joinColumns = @JoinColumn(name = "reservation_id")
    )
    @Comment("예약된 승객들")
    private List<Passenger> passengers;

//    public static Reservation create(String airline, RouteInfo routeInfo, List<Passenger> passengers, TotalAmount totalAmount) {
//        // 예약번호 생성 로직 필요
//        String reservationNumber = "TEST-NUMBER";
//
//        // 검증 로직 필요
//        if (airline == null || airline.isBlank()) {
//            throw new IllegalArgumentException("항공사는 필수입니다");
//        }
//
//        Reservation reservation = new Reservation();
//        reservation.reservationNumber = reservationNumber;
//        reservation.airline = airline;
//        reservation.routeInfo = routeInfo;
//        reservation.passengers = List.copyOf(passengers);
//        reservation.totalAmount = totalAmount;
//        reservation.reservationStatus = ReservationStatus.AWAIT_CONFIRMED;
//
//        return reservation;
//    }
}

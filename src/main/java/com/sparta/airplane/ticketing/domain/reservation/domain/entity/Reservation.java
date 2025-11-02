package com.sparta.airplane.ticketing.domain.reservation.domain.entity;

import com.sparta.airplane.ticketing.domain.reservation.domain.ReservationNumberGenerator;
import com.sparta.airplane.ticketing.domain.reservation.domain.vo.Passenger;
import com.sparta.airplane.ticketing.domain.reservation.domain.vo.ReservationNumber;
import com.sparta.airplane.ticketing.domain.reservation.domain.vo.ReservationStatus;
import com.sparta.airplane.ticketing.domain.reservation.domain.vo.RouteInfo;
import com.sparta.airplane.ticketing.domain.reservation.domain.vo.TotalAmount;
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

/**
 * 예약 관련 Aggregate Root
 *
 * <역할>
 * - 예약 생명주기(생성, 확정, 취소)를 관리하는 핵심 도메인 객체
 * - 예약에 포함된 승객, 경로, 금액 정보의 일관성을 보장
 * - 예약 번호를 통해 외부에서 식별되고 조회됨
 *
 * <설계 이유>
 * - Aggregate Root로 설계한 이유:
 *   1. 예약은 독립적인 비즈니스 경계를 가지며, 예약 상태 변경 시 모든 하위 정보의 일관성 보장 필요
 *   2. 승객 목록, 경로 정보, 총 금액은 예약을 통해서만 변경되어야 함
 *   3. 예약 취소 가능 여부 같은 비즈니스 규칙이 예약 컨텍스트 내에서 결정됨
 *
 * <Aggregate 경계>
 * - Reservation (루트)
 * - ReservationNumber, RouteInfo, TotalAmount (값 객체)
 * - Passenger (컬렉션 값 객체)
 * - ReservationStatus,
 */
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

    public static Reservation create(String airline, RouteInfo routeInfo, List<Passenger> passengers, ReservationNumberGenerator generator) {
        // 검증 로직 필요
        if (airline == null || airline.isBlank()) {
            throw new IllegalArgumentException("항공사는 필수입니다");
        }

        Reservation reservation = new Reservation();
        reservation.reservationNumber = generator.generate();
        reservation.airline = airline;
        reservation.routeInfo = routeInfo;
        reservation.passengers = List.copyOf(passengers);
        reservation.totalAmount = TotalAmount.from(passengers);
        reservation.reservationStatus = ReservationStatus.AWAIT_CONFIRMED;

        return reservation;
    }

    public void confirm() {
        this.reservationStatus = ReservationStatus.CONFIRMED;
    }

    public boolean isCancellable() {
        if (this.reservationStatus == ReservationStatus.CANCELLED) {
            return false;
        }

        if (this.reservationStatus != ReservationStatus.CONFIRMED) {
            return false;
        }

        return true;
    }

    public void cancel() {
        if (!isCancellable()) {
            throw new IllegalStateException("취소할 수 없는 예약입니다");
        }
        this.reservationStatus = ReservationStatus.CANCELLED;
    }

    public boolean isConfirmed() {
        return this.reservationStatus == ReservationStatus.CONFIRMED;
    }
}

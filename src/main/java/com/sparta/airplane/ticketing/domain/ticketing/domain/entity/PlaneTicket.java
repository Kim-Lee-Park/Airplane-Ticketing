package com.sparta.airplane.ticketing.domain.ticketing.domain.entity;

import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.Airport;
import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.Amount;
import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.BaggageCode;
import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.FareClass;
import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.PassengerName;
import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.ReservationId;
import com.sparta.airplane.ticketing.domain.ticketing.domain.vo.TicketStatus;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
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
@Table(name = "plane_ticket")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaneTicket extends AbstractAggregateRoot<PlaneTicket> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Embedded
    @AttributeOverride(name = "reservationId", column = @Column(name = "reservation_id", nullable = false))
    private ReservationId reservationId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount", nullable = false))
    })
    private Amount amount;

    //from to eta 한 객체로 묶기 -> 비교하는 용도로 일단 가만히 두기
    @Embedded
    @AttributeOverride(name = "airportCode", column = @Column(name = "departure_airport", nullable = false))
    private Airport from;

    @Embedded
    @AttributeOverride(name = "airportCode", column = @Column(name = "arrival_airport", nullable = false))
    private Airport to;

    // 예상 도착 시간
    @Column(nullable = false)
    private LocalDateTime eta;

    // 경유지 -> Collection Table 사용해서 수정
    @Column(nullable = false)
    private String via;

    @Embedded
    @AttributeOverride(name = "passengerName", column = @Column(name = "passenger_name", nullable = false))
    private PassengerName passengerName;

    @Enumerated(EnumType.STRING)
    @Column(name = "fareclass", nullable = false)
    private FareClass fareClass;

    @Embedded
    @AttributeOverride(name = "baggageCode", column = @Column(name = "baggage_code"))
    private BaggageCode baggageCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TicketStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * 티켓 생성 팩토리 메서드
     */
    public static PlaneTicket create(
            ReservationId reservationId,
            Amount amount,
            Airport from,
            Airport to,
            LocalDateTime eta,
            String via,
            PassengerName passengerName,
            FareClass fareClass,
            BaggageCode baggageCode,
            TicketStatus ticketStatus
            ) {

        PlaneTicket ticket = new PlaneTicket();
        ticket.reservationId = reservationId;
        ticket.amount = amount;
        ticket.from = from;
        ticket.to = to;
        ticket.eta = eta;
        ticket.via = via;
        ticket.passengerName = passengerName;
        ticket.fareClass = fareClass;
        ticket.baggageCode = baggageCode;
        ticket.status = ticketStatus;
        ticket.createdAt = LocalDateTime.now();

        // 비즈니스 규칙 검증
        ticket.validate();

        return ticket;
    }

    private void validate() {
        if (!amount.isPositive()) {
            throw new IllegalArgumentException("금액은 0보다 커야 합니다");
        }
        if (from.getAirportCode().equals(to.getAirportCode())) {
            throw new IllegalArgumentException("출발지와 도착지는 같을 수 없습니다");
        }
        if (eta == null) {
            throw new IllegalArgumentException("예상 도착 시간은 필수입니다");
        }
        if (via == null || via.isBlank()) {
            throw new IllegalArgumentException("경유지 정보는 필수입니다");
        }
    }

}
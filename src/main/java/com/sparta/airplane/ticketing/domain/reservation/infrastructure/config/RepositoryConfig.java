package com.sparta.airplane.ticketing.domain.reservation.infrastructure.config;

import com.sparta.airplane.ticketing.domain.reservation.domain.repository.ReservationRepository;
import com.sparta.airplane.ticketing.domain.reservation.infrastructure.repository.JpaReservationRepository;
import com.sparta.airplane.ticketing.domain.reservation.infrastructure.repository.ReservationRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public ReservationRepository reservationRepository(JpaReservationRepository jpaReservationRepository) {
        return new ReservationRepositoryAdapter(jpaReservationRepository);
    }
}

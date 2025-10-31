package com.sparta.airplane.ticketing.domain.ticketing.infrastructure.config;

import com.sparta.airplane.ticketing.domain.ticketing.domain.repository.FlightRepository;
import com.sparta.airplane.ticketing.domain.ticketing.infrastructure.repository.FlightRepositoryAdapter;
import com.sparta.airplane.ticketing.domain.ticketing.infrastructure.repository.JpaFlightRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public FlightRepository flightRepository(JpaFlightRepository jpaFlightRepository) {
        return new FlightRepositoryAdapter(jpaFlightRepository);
    }
}

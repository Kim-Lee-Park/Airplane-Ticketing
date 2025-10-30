package com.sparta.airplane.ticketing.domain.reservation.application.command;

import com.sparta.airplane.ticketing.domain.reservation.domain.vo.FareClass;
import com.sparta.airplane.ticketing.domain.reservation.domain.vo.Gender;
import com.sparta.airplane.ticketing.domain.reservation.domain.vo.Passenger;
import com.sparta.airplane.ticketing.domain.reservation.domain.vo.SpecialService;
import java.time.LocalDate;

public record PassengerCommand(
    String name,
    Gender gender,
    LocalDate birthDate,
    FareClass fareClass,
    String specialServiceCode,
    String additionalServiceCode,
    String baggageCode,
    Boolean inFlightMeal
) {
    public Passenger toDomain() {
        SpecialService specialService = null;

        if (specialServiceCode != null || additionalServiceCode != null ||
            baggageCode != null || inFlightMeal != null) {
            boolean meal = inFlightMeal != null && inFlightMeal;
            specialService = SpecialService.of(
                specialServiceCode,
                additionalServiceCode,
                baggageCode,
                meal
            );
        }

        return new Passenger(
            name,
            gender,
            birthDate,
            fareClass,
            specialService
        );
    }
}

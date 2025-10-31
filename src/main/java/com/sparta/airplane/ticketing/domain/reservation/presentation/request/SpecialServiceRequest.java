package com.sparta.airplane.ticketing.domain.reservation.presentation.request;

import com.sparta.airplane.ticketing.domain.reservation.domain.vo.SpecialService;

public record SpecialServiceRequest(
    String specialServiceCode,
    String additionalServiceCode,
    String baggageCode,
    Boolean inFlightMeal
) {
    public SpecialService toDomain() {
        boolean meal = inFlightMeal != null && inFlightMeal;

        return SpecialService.of(
            specialServiceCode,
            additionalServiceCode,
            baggageCode,
            meal
        );
    }
}

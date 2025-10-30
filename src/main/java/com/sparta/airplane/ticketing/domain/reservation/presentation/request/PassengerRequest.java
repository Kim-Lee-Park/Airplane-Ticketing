package com.sparta.airplane.ticketing.domain.reservation.presentation.request;

import com.sparta.airplane.ticketing.domain.reservation.application.command.PassengerCommand;
import com.sparta.airplane.ticketing.domain.reservation.domain.vo.FareClass;
import com.sparta.airplane.ticketing.domain.reservation.domain.vo.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record PassengerRequest(
    @NotBlank(message = "이름은 필수입니다")
    String name,
    @NotNull(message = "성별은 필수입니다")
    Gender gender,
    @NotNull(message = "생년월일은 필수입니다")
    LocalDate birthDate,
    @NotNull(message = "운임 클래스는 필수입니다")
    FareClass fareClass,
    @Valid
    SpecialServiceRequest specialService
) {
    public PassengerCommand toCommand() {
        return new PassengerCommand(
            name,
            gender,
            birthDate,
            fareClass,
            specialService != null ? specialService.specialServiceCode() : null,
            specialService != null ? specialService.additionalServiceCode() : null,
            specialService != null ? specialService.baggageCode() : null,
            specialService != null ? specialService.inFlightMeal() : null
        );
    }
}

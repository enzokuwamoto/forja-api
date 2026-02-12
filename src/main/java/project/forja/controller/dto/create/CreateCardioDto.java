package project.forja.controller.dto.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import project.forja.entity.SportsType;

public record CreateCardioDto(
        @NotNull(message = "A distância é obrigatória.")
        @Positive(message = "Distância inválida.")
        Double distanceKm,
        @NotNull(message = "O pace é obrigatório.")
        @Positive(message = "Pace deve ser maior que 0.")
        Double pace,
        @PositiveOrZero(message = "A elevação precisar ser maior que 0.")
        Double elevationGain,
        @NotNull(message = "O tipo de esporte é obrigatório.")
        SportsType sportsType) {
}

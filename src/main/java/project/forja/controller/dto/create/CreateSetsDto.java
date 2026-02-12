package project.forja.controller.dto.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateSetsDto(
        @NotNull(message = "O número de repetições é obrigatório.")
        @Min(value = 1, message = "Mínimo de 1 repetição.")
        Integer reps,
        @NotNull(message = "A carga é obrigatória.")
        @PositiveOrZero(message = "A carga não pode ser negativa.")
        Double weightKg) {
}

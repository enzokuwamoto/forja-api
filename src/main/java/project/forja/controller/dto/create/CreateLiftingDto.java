package project.forja.controller.dto.create;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record CreateLiftingDto(
        @NotNull(message = "O ID do exercício é obrigatório.")
        UUID exerciseId,
        String notes,
        @NotEmpty(message = "Adicione pelo menos uma série.")
        @Valid
        List<CreateSetsDto> sets) {
}

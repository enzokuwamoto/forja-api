package project.forja.controller.dto.create;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import project.forja.entity.TerrainType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CreateSessionDto(
        @NotNull(message = "Usuário obrigatório.")
        UUID userId,
        @NotNull(message = "A data não pode estar vazia.")
        @PastOrPresent(message = "Data de exercício inválida.")
        LocalDateTime dateTime,
        @Positive(message = "Duração deve ser maior que zero.")
        Integer durationTime,
        @Min(1) @Max(10)
        Integer intensityRpe,
        String battleLog,
        TerrainType terrainType,
        @Valid
        CreateCardioDto cardio,
        @Valid
        List<CreateLiftingDto> lifts) {
}

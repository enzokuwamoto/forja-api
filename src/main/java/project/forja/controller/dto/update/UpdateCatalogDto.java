package project.forja.controller.dto.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import project.forja.entity.MuscleGroup;

public record UpdateCatalogDto
        (@NotBlank(message = "O nome do exercício é obrigatório.")
         String name,
         @NotNull(message = "O grupo muscular é obrigatório.")
         MuscleGroup muscleGroup) {
}

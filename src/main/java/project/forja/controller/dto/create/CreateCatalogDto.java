package project.forja.controller.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import project.forja.entity.MuscleGroup;

public record CreateCatalogDto (
        @NotBlank(message = "O nome do exercício é obrigatório.")
        @Pattern(regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ\\s]+$", message = "Nome do exercício inválido.")
        String name,
        @NotNull(message = "O grupo muscular é obrigatório.")
        MuscleGroup muscleGroup) {
}

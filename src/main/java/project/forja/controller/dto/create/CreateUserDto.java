package project.forja.controller.dto.create;

import jakarta.validation.constraints.*;
import project.forja.entity.GoalType;

import java.time.LocalDate;

public record CreateUserDto(
        @NotBlank(message = "O nome é obrigatório.")
        @Pattern(regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ\\s]+$", message = "Digite o nome e sobrenome. Nome não pode conter números ou símbolos")
        @Size(min = 2, max = 80, message = "Nome muito curto.")
        String name,
        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "O e-mail não é válido.")
        String email,
        @NotBlank(message = "A senha é obrigatória.")
        @Size(min = 8, message = "A senha precisa ter no mínimo 8 caracteres.")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
                message = "A senha precisa conter pelo menos uma letra minúscula, uma maiúscula, um número e um caractere especial.")
        String password,
        @NotNull(message = "O peso é obrigatória.")
        @Positive(message = "Peso inválido.")
        Double currentWeight,
        @NotNull(message = "A altura é obrigatória.")
        @Positive(message = "Altura inválida.")
        @DecimalMax(value = "2.20", message = "Valide a altura.")
        Double height,
        Double targetWeight,
        @PastOrPresent(message = "Data inválida.")
        LocalDate startDate,
        GoalType goalType) {
}
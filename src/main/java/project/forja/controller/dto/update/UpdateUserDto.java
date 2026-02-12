package project.forja.controller.dto.update;

import jakarta.validation.constraints.*;
import project.forja.entity.GoalType;

import java.time.LocalDate;

public record UpdateUserDto(
        @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\\\s]*$", message = "Nome não pode conter números ou símbolos")
        @Size(min = 2, max = 80, message = "Nome muito curto.")
        String name,
        @Email(message = "O e-mail não é válido.")
        String email,
        @Size(min = 8, message = "A senha precisa ter no mínimo 8 caracteres.")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
                message = "A senha precisa conter pelo menos uma letra minúscula, uma maiúscula, um número e um caractere especial.")
        String password,
        @Positive
        Double currentWeight,
        @Positive
        Double height,
        Double targetWeight,
        @PastOrPresent(message = "Data inválida.")
        LocalDate startDate,
        GoalType goalType) {
}

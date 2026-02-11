package project.forja.controller.dto.response;

import project.forja.entity.GoalType;

import java.time.LocalDate;
import java.util.UUID;

public record UserResponseDto(UUID userId, String name, String email, String password, Double currentWeight, Double height, Double targetWeight, LocalDate startDate, GoalType goalType) {
}

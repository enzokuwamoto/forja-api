package project.forja.controller.dto.response;

import project.forja.entity.ExerciseCatalog;
import project.forja.entity.MuscleGroup;

import java.util.UUID;

public record ExerciseCatalogResponseDto(UUID id, MuscleGroup muscleGroup, String name) {
}

package project.forja.controller.dto.create;

import project.forja.entity.TerrainType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CreateSessionDto(UUID userId, LocalDateTime dateTime, int durationTime, int intensityRpe, String battleLog, TerrainType terrainType, CreateCardioDto cardio, List<CreateLiftingDto> lifts) {
}

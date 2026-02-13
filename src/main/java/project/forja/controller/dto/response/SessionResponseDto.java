package project.forja.controller.dto.response;

import project.forja.entity.TerrainType;

import java.time.LocalDateTime;
import java.util.UUID;

public record SessionResponseDto(UUID sessionId, LocalDateTime dateTime, int duration, int intensityRpe,
                                 String battleLog, TerrainType terrainType, UUID userId) {
}

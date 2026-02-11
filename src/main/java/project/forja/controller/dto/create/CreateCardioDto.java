package project.forja.controller.dto.create;

import project.forja.entity.SportsType;

import java.util.UUID;

public record CreateCardioDto(Double distanceKm, Double pace, Double elevationGain, SportsType sportsType) {
}

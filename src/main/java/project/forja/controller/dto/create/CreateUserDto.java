package project.forja.controller.dto.create;

import project.forja.entity.GoalType;

import java.time.LocalDate;

public record CreateUserDto(String name, String email, String password, Double currentWeight, Double height, Double targetWeight, LocalDate startDate, GoalType goalType){
}
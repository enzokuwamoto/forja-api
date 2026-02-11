package project.forja.controller.dto.update;

import project.forja.entity.GoalType;

public record UpdateUserDto(String name, String email, String password, Double currentWeight, Double height, Double targetWeight, GoalType goalType){
}

package project.forja.service;

import org.springframework.stereotype.Service;
import project.forja.controller.dto.create.CreateCatalogDto;
import project.forja.controller.dto.update.UpdateCatalogDto;
import project.forja.entity.ExerciseCatalog;
import project.forja.repository.ExerciseCatalogRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ExerciseService {

    private final ExerciseCatalogRepository exerciseCatalogRepository;

    public ExerciseService(ExerciseCatalogRepository exerciseCatalogRepository) {
        this.exerciseCatalogRepository = exerciseCatalogRepository;
    }


    public List<ExerciseCatalog> listExercises() {
        return exerciseCatalogRepository.findAll();
    }

    public void updateUserById(String userId,
                               UpdateCatalogDto updateCatalogDto) {

        var id = UUID.fromString(userId);
        var userEntity = exerciseCatalogRepository.findById(id);
        if (userEntity.isPresent()) {
            var exercise = userEntity.get();
            if (updateCatalogDto.name() != null) {
                exercise.setName(updateCatalogDto.name());
            }
            if (updateCatalogDto.muscleGroup() != null) {
                exercise.setMuscleGroup(updateCatalogDto.muscleGroup());
            }
            exerciseCatalogRepository.save(exercise);
        }
    }

    public void deleteExerciseById(String userId) {
        var id = UUID.fromString(userId);
        var userExists = exerciseCatalogRepository.existsById(id);

        if (userExists) {
            exerciseCatalogRepository.deleteById(id);
        }
    }

    public void createBatch(List<CreateCatalogDto> createCatalogDto) {
        var exercises = createCatalogDto.stream()
                .map(dto -> new ExerciseCatalog(dto.muscleGroup(), dto.name()))
                .toList();
        exerciseCatalogRepository.saveAll(exercises);
    }
}

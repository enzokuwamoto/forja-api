package project.forja.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.forja.controller.dto.create.CreateCatalogDto;
import project.forja.controller.dto.response.ExerciseCatalogResponseDto;
import project.forja.controller.dto.update.UpdateCatalogDto;
import project.forja.entity.ExerciseCatalog;
import project.forja.entity.MuscleGroup;
import project.forja.repository.ExerciseCatalogRepository;
import project.forja.service.ExerciseCatalogService;
import project.forja.service.ExerciseService;

import java.util.List;

@RestController
@RequestMapping("/v1/catalog")
public class ExerciseCatalogController {

    private ExerciseService exerciseService;
    private ExerciseCatalogService exerciseCatalogService;

    public ExerciseCatalogController(ExerciseService exerciseService, ExerciseCatalogService exerciseCatalogService) {
        this.exerciseService = exerciseService;
        this.exerciseCatalogService = exerciseCatalogService;
    }

    @PostMapping("/exercises")
    public ResponseEntity<Void> createCatalog(@RequestBody CreateCatalogDto createCatalogDto) {
        exerciseService.createCatalog(createCatalogDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<ExerciseCatalogResponseDto>> listCatalog(
        @RequestParam(required = false) String muscleGroup,
        @RequestParam(required = false) String name){
        var list = exerciseCatalogService.listExercises(muscleGroup, name);
        return ResponseEntity.ok(list);
    }

    @PutMapping("/exercises/{userId}")
    public ResponseEntity<Void> updateExerciseById(@PathVariable("userId") String userId,
                                                   @RequestBody UpdateCatalogDto updateCatalogDto) {
        exerciseService.updateUserById(userId, updateCatalogDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/exercises/{userId}")
    public ResponseEntity<Void> deleteExerciseById(@PathVariable("userId") String userId) {
        exerciseService.deleteExerciseById(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/batch")
    public ResponseEntity<Void> createBatch(@RequestBody List<CreateCatalogDto> createCatalogDto) {
        exerciseService.createBatch(createCatalogDto);
        return ResponseEntity.ok().build();
    }

}

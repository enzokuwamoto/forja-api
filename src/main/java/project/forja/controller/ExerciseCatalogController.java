package project.forja.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import project.forja.controller.dto.create.CreateCatalogDto;
import project.forja.controller.dto.response.ExerciseCatalogResponseDto;
import project.forja.controller.dto.update.UpdateCatalogDto;
import project.forja.service.ExerciseCatalogService;
import project.forja.service.ExerciseService;

import java.util.List;

@RestController
@RequestMapping("/v1/catalog")
public class ExerciseCatalogController {

    @Autowired
    private ExerciseService exerciseService;
    @Autowired
    private ExerciseCatalogService exerciseCatalogService;


    @PostMapping
    public ResponseEntity<Void> createCatalog(@Valid @RequestBody CreateCatalogDto createCatalogDto) {
        exerciseCatalogService.createCatalog(createCatalogDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ExerciseCatalogResponseDto>> listCatalog(
            @RequestParam(required = false) String muscleGroup,
            @RequestParam(required = false) String name) {
        var list = exerciseCatalogService.listExercises(muscleGroup, name);
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateExerciseById(@PathVariable("userId") String userId,
                                                   @RequestBody UpdateCatalogDto updateCatalogDto) {
        exerciseService.updateUserById(userId, updateCatalogDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteExerciseById(@PathVariable("userId") String userId) {
        exerciseService.deleteExerciseById(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/batch")
    public ResponseEntity<Void> createBatch(@RequestBody List<CreateCatalogDto> createCatalogDto) {
        exerciseService.createBatch(createCatalogDto);
        return ResponseEntity.ok().build();
    }

    // Tratamento de exceções que faltam o ID na solicitação

    @PutMapping({"", "/"})
    public ResponseEntity<Void> updateExerciseWithouId() {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Id do exercício é obrigatório.");
    }

    @DeleteMapping({"", "/"})
    public ResponseEntity<Void> deleteExerciseWithouId() {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Id do exercício é obrigatório.");
    }

}

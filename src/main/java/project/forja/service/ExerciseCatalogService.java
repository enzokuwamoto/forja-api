package project.forja.service;

import org.springframework.stereotype.Service;
import project.forja.controller.dto.response.ExerciseCatalogResponseDto;
import project.forja.entity.ExerciseCatalog;
import project.forja.entity.MuscleGroup;
import project.forja.repository.ExerciseCatalogRepository;

import java.text.Normalizer;
import java.util.List;

@Service
public class ExerciseCatalogService {

    private final ExerciseCatalogRepository repository;

    public ExerciseCatalogService(ExerciseCatalogRepository repository) {
        this.repository = repository;
    }



    public List<ExerciseCatalogResponseDto> listExercises(String muscleGroupString, String name){
        // Variável para conversão de ENUM para String
        MuscleGroup muscleGroupEnum = null;
        String nameNoSpace = null;

        // Lógica para transformar Enum em String. Ex.: muscleGroup = MuscleGroup.PEITO // busca get = &muscleGroup = peito
        if(muscleGroupString != null && !muscleGroupString.isEmpty()){
            try{
                muscleGroupEnum = MuscleGroup.valueOf(muscleGroupString.toUpperCase());
            } catch (IllegalArgumentException e) {
                muscleGroupEnum = null;
            }
        }

        // Lógica para retirar espaços no de-para do bd com o get. Ex.: nome exercicio = Supino Inclinado // busca get = &name=supinoinclinado
        if(name != null){
            nameNoSpace = normalizeString(name);
            System.out.println("Entrou no if? Sim!");
        }

        var exercises = repository.findWithFilters(muscleGroupEnum, nameNoSpace);

        return exercises.stream()
                .map(this::mapToDto)
                .toList();
    }

    private ExerciseCatalogResponseDto mapToDto(ExerciseCatalog catalog){
        return new ExerciseCatalogResponseDto(
                catalog.getId(),
                catalog.getMuscleGroup(),
                catalog.getName()
        );
    }

    private String normalizeString(String input){
        if (input == null) return null;
        // Separar o acento da letra - ex.: á vira "a" + "´"
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        // Remover os acentos
        String semAcento = normalized.replaceAll("\\p{M}", "");
        //Remove o que não for letra (espaços, hífens e parênteses)
        return semAcento.replaceAll("[^a-zA-Z0-9]","").toLowerCase();
    }
}

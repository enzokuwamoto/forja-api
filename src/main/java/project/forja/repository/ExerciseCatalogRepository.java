package project.forja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.forja.entity.ExerciseCatalog;
import project.forja.entity.MuscleGroup;
import project.forja.entity.SetLog;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExerciseCatalogRepository extends JpaRepository<ExerciseCatalog, UUID> {

    @Query("SELECT e FROM ExerciseCatalog e WHERE " +
            "(:muscleGroup IS NULL OR e.muscleGroup = :muscleGroup) AND " +
            "(:name IS NULL OR " +
            // Removemos hifens, espaços, parênteses e pontos do nome NO BANCO
            "LOWER(REPLACE(REPLACE(REPLACE(REPLACE(e.name, '-', ''), ' ', ''), '(', ''), ')', '')) " +
            "LIKE LOWER(CONCAT('%', :name, '%')))")
    List<ExerciseCatalog> findWithFilters(@Param("muscleGroup") MuscleGroup muscleGroup,
                                          @Param("name") String name);
}


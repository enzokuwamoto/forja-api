package project.forja.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "exercise_catalog")
public class ExerciseCatalog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "exercise_catalog_id")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "muscle_group", unique = true)
    private MuscleGroup muscleGroup;

    @Column(name = "name_exercise", nullable = false, unique = true)
    private String name;


    public ExerciseCatalog() {
    }

    public ExerciseCatalog(MuscleGroup muscleGroup, String name) {
        this.muscleGroup = muscleGroup;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MuscleGroup getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(MuscleGroup muscleGroup) {
        this.muscleGroup = muscleGroup;
    }
}

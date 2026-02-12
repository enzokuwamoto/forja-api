package project.forja.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "lifting_log")
public class LiftingLog{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "lifting_log_id")
    private UUID id;

    @Column(name = "notes")
    private String notes;

    // Training Session -> Lifting Log (1 para vários)
    // Um treinamento de força tem vários exercícios inseridos.
    @ManyToOne
    @JoinColumn(name = "training_session_id")
    private TrainingSession trainingSession;

    // Lifting Log -> Set Log (1 para vários)
    // Um exercício realizado tem várias séries.
    @OneToMany(mappedBy = "liftingLog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SetLog> sets = new ArrayList<>();

    // Exercise Catalog -> Lifting Log (1 para vários)
    // Um tipo de exercício do catálogo aparece em vários treinos diferentes. A fonte da verdade e os treinos apontam para ele.
    // Sentido unidirecional - Boa prática, muitas pessoas farão o mesmo exercício e não será possível extrair todos os exercícios da história do sistema.
    @ManyToOne
    @JoinColumn(name = "exercise_catalog_id")
    private ExerciseCatalog exerciseCatalog;

    public LiftingLog(String notes) {
    }

    public LiftingLog(String notes, ExerciseCatalog exerciseCatalog) {
        this.notes = notes;
        this.sets = new ArrayList<>();
        this.exerciseCatalog = exerciseCatalog;
        this.sets = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public TrainingSession getTrainingSession() {
        return trainingSession;
    }

    public void setTrainingSession(TrainingSession trainingSession) {
        this.trainingSession = trainingSession;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<SetLog> getSets() {
        return sets;
    }

    public void setSets(List<SetLog> sets) {
        this.sets = new ArrayList<>();
    }

    public ExerciseCatalog getExerciseCatalog() {
        return exerciseCatalog;
    }

    public void setExerciseCatalog(ExerciseCatalog exerciseCatalog) {
        this.exerciseCatalog = exerciseCatalog;
    }

    // Metodo auxiliar para manter a rotação de dados segura
    public void addSets(SetLog sets){
        this.sets.add(sets);
        sets.setLiftingLog(this);
    }
}

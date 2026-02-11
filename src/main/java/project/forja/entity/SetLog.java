package project.forja.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "set_log")
public class SetLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "set_log_id")
    private UUID id;

    @Column(name = "serie_number")
    private int serie_number;

    @Column(name = "reps")
    private int reps;

    @Column(name = "weight_kg")
    private Double weightKg;

    @ManyToOne
    @JoinColumn(name = "lifting_log_id")
    private LiftingLog liftingLog;

    public SetLog(int serie_number, int reps, Double weightKg) {
        this.serie_number = serie_number;
        this.reps = reps;
        this.weightKg = weightKg;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public Double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(Double weightKg) {
        this.weightKg = weightKg;
    }

    public LiftingLog getLiftingLog() {
        return liftingLog;
    }

    public void setLiftingLog(LiftingLog liftingLog) {
        this.liftingLog = liftingLog;
    }
}

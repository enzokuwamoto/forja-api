package project.forja.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "cardio_log")
public class CardioLog {



    @Column(name = "distance_km")
    private Double distanceKm;

    @Column(name = "pace")
    private Double pace;@Id
    private UUID id;

    @Column(name = "elevation_gain")
    private Double elevationGain;

    @Enumerated(EnumType.STRING)
    @Column(name = "sports_type")
    private SportsType sportsType;

    // Sem implementação de herança pois o usuário pode treinar híbrido, gerando assim uma anexo de Cardio e Força simultâneos.
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private TrainingSession trainingSession;

    public CardioLog() {
    }

    public CardioLog(Double distanceKm, Double pace, Double elevationGain, SportsType sportsType) {
        this.distanceKm = distanceKm;
        this.pace = pace;
        this.elevationGain = elevationGain;
        this.sportsType = sportsType;
    }

    public Double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(Double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public Double getPace() {
        return pace;
    }

    public void setPace(Double pace) {
        this.pace = pace;
    }

    public Double getElevationGain() {
        return elevationGain;
    }

    public void setElevationGain(Double elevationGain) {
        this.elevationGain = elevationGain;
    }

    public SportsType getSportsType() {
        return sportsType;
    }

    public void setSportsType(SportsType sportsType) {
        this.sportsType = sportsType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TrainingSession getTrainingSession() {
        return trainingSession;
    }

    public void setTrainingSession(TrainingSession trainingSession) {
        this.trainingSession = trainingSession;
    }
}

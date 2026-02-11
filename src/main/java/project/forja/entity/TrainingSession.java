package project.forja.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "training_session")
public class TrainingSession {

    @Id
    @Column(name = "training_session_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "duration_time")
    private int durationTime;

    @Column(name = "intensity_rpe")
    private int intensityRpe;

    @Column(name = "battle_log")
    private String battleLog;

    @Enumerated(EnumType.STRING)
    @Column(name = "terrain_type")
    private TerrainType terrainType;

    // Athletes -> Training Session (1 -> *)
    // Vários treinos podem ser feitos por um atleta
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Training Session -> Cardio Log (1 para 1 ou 0)
    // Um treino pode ou não ser cardio. Não pode haver 2 cardios em um só treino.
    // orphanRemoval -> se o cardio for setado como "null", é deletado do banco.
    @OneToOne(mappedBy = "trainingSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private CardioLog cardioLog;


    // Training Session -> Lifting Log (1 para vários)
    // Um treinamento de força é composto por vários exercícios.
    // orphanRemoval -> se o cardio for setado como "null", é deletado do banco.
    @OneToMany(mappedBy = "trainingSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LiftingLog> liftingLogs = new ArrayList<>();

    public TrainingSession() {
    }

    public TrainingSession(User user, LocalDateTime dateTime, int durationTime, int intensityRpe, String battleLog, TerrainType terrainType) {
        this.user = user;
        this.dateTime = dateTime;
        this.durationTime = durationTime;
        this.intensityRpe = intensityRpe;
        this.battleLog = battleLog;
        this.terrainType = terrainType;
        this.liftingLogs = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(int durationTime) {
        this.durationTime = durationTime;
    }

    public int getIntensityRpe() {
        return intensityRpe;
    }

    public void setIntensityRpe(int intensityRpe) {
        this.intensityRpe = intensityRpe;
    }

    public String getBattleLog() {
        return battleLog;
    }

    public void setBattleLog(String battleLog) {
        this.battleLog = battleLog;
    }

    public TerrainType getTerrainType() {
        return terrainType;
    }

    public void setTerrainType(TerrainType terrainType) {
        this.terrainType = terrainType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CardioLog getCardioLog() {
        return cardioLog;
    }

    public void setCardioLog(CardioLog cardioLog) {
        this.cardioLog = cardioLog;
    }

    public List<LiftingLog> getLiftingLog() {
        return liftingLogs;
    }

    public void setLiftingLog(List<LiftingLog> liftingLog) {
        this.liftingLogs = liftingLog;
    }
}
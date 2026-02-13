package project.forja.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "current_weight")
    private Double currentWeight;

    @Column(name = "height")
    private Double height;

    @Column(name = "target_weight")
    private Double targetWeight;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "goal_type")
    @Enumerated(EnumType.STRING)
    private GoalType goalType;

    @CreationTimestamp
    private Instant creationTimestamp;
    @UpdateTimestamp
    private Instant updatedTimeStamp;

    // Athletes -> Training Session (1 -> *)
    // Um atleta pode fazer vários treinos
    @OneToMany(mappedBy = "user")
    private List<TrainingSession> trainingSessions;

    public User() {
    }

    public User(UUID userId, String name, String email, String password, Double currentWeight, Double height, Double targetWeight, LocalDate startDate, GoalType goalType, Instant creationTimestamp, Instant updatedTimeStamp) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.currentWeight = currentWeight;
        this.height = height;
        this.targetWeight = targetWeight;
        this.startDate = startDate;
        this.goalType = goalType;
        this.creationTimestamp = creationTimestamp;
        this.updatedTimeStamp = updatedTimeStamp;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(Double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(Double targetWeight) {
        this.targetWeight = targetWeight;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public GoalType getGoalType() {
        return goalType;
    }

    public void setGoalType(GoalType goalType) {
        this.goalType = goalType;
    }

    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Instant creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public Instant getUpdatedTimeStamp() {
        return updatedTimeStamp;
    }

    public void setUpdatedTimeStamp(Instant updatedTimeStamp) {
        this.updatedTimeStamp = updatedTimeStamp;
    }

    public List<TrainingSession> getTrainingSessions() {
        return trainingSessions;
    }

    public void setTrainingSessions(List<TrainingSession> trainingSessions) {
        this.trainingSessions = trainingSessions;
    }

}

package project.forja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.forja.entity.TrainingSession;

import java.util.List;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<TrainingSession, UUID> {
    // SQL: SELECT * FROM training_session WHERE user_id = ?
    List<TrainingSession> findByUserUserId(UUID userId);
}

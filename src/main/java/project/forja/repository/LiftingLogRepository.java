package project.forja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.forja.entity.LiftingLog;

import java.util.UUID;

@Repository
public interface LiftingLogRepository extends JpaRepository<LiftingLog, UUID> {
}

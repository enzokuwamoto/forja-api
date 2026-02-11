package project.forja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.forja.entity.LiftingLog;
import project.forja.entity.SetLog;

import java.util.UUID;

@Repository
public interface SetLogRepository extends JpaRepository<SetLog, UUID> {
}

package project.forja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.forja.entity.CardioLog;

import java.util.UUID;

@Repository
public interface CardioLogRepository extends JpaRepository<CardioLog, UUID> {
}

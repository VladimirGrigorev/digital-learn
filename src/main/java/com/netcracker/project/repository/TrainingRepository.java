package com.netcracker.project.repository;

import com.netcracker.project.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository for trainings
 */
@Repository
public interface TrainingRepository extends JpaRepository<Training, UUID> {
}

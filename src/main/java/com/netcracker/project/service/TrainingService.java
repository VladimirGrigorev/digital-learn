package com.netcracker.project.service;

import com.netcracker.project.entity.Training;
import com.netcracker.project.entity.dto.TrainingDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TrainingService {

    /**
     * Add training
     * @param trainingDTO - training data
     * @param userLogin - username
     * @return training
     */
    Optional<Training> addTraining(TrainingDTO trainingDTO, String userLogin);

    /**
     * Edit training
     * @param trainingId - training id
     * @param trainingDTO - training data
     * @param userLogin - username
     * @return training
     */
    Optional<Training> editTraining(UUID trainingId, TrainingDTO trainingDTO,
                                    String userLogin);

    /**
     * Find all trainings
     * @return list trainings
     */
    List<Training> findAll();

    /**
     * Find training by id
     * @param trainingId - training id
     * @return training
     */
    Optional<Training> findById(UUID trainingId);

    /**
     * Delete training
     * @param id - training id
     * @param userLogin - username
     * @return training
     */
    Optional<Training> delete(UUID id, String userLogin);

    /**
     * Get trainings by user
     * @param userLogin - username
     * @return list training
     */
    List<Training> getUserTrainings(String userLogin);

    /**
     * Get trainings by creator
     * @param userLogin - username
     * @return list trainings
     */
    List<Training> getMentorTrainings(String userLogin);
}

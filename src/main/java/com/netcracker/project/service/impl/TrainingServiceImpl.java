package com.netcracker.project.service.impl;

import com.netcracker.project.entity.Category;
import com.netcracker.project.entity.Training;
import com.netcracker.project.entity.dto.TrainingDTO;
import com.netcracker.project.entity.enums.RoleName;
import com.netcracker.project.repository.CategoryRepository;
import com.netcracker.project.repository.TrainingRepository;
import com.netcracker.project.repository.UserRepository;
import com.netcracker.project.service.TrainingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service(value = "trainingService")
public class TrainingServiceImpl implements TrainingService {

    /**
     * Beans for work with training
     */
    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    /**
     * Constructor
     */
    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository,
                               UserRepository userRepository,
                               CategoryRepository categoryRepository) {
        this.trainingRepository = trainingRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Check is training exist
     */
    private boolean isTrainingExists(UUID trainingId) {
        return trainingRepository.findById(trainingId).isPresent();
    }

    /**
     * Check is author is admin
     */
    private boolean isAuthorOrAdmin(String userLogin, UUID trainingId) {
        // If user author or admin - true
        return (userRepository.findByEmail(userLogin).get().getId().equals(
                trainingRepository.findById(trainingId).get().getUser().getId()) ||
                userRepository.findByEmail(userLogin).get().getRoles()
                        .contains(RoleName.ROLE_ADMIN));
    }

    /**
     * Get trainings by creator
     * @param userLogin - username
     * @return list trainings
     */
    @Override
    public List<Training> getMentorTrainings(String userLogin) {
        log.info("Mentor trainings were send");
        return new ArrayList<>(userRepository.findByEmail(userLogin).get().getTrainings());
    }

    /**
     * Get trainings by user
     * @param userLogin - username
     * @return list training
     */
    @Override
    public List<Training> getUserTrainings(String userLogin) {
        log.info("User trainings were send");
        List<Training> trainings = new ArrayList<>();
        for (UUID id : userRepository.findByEmail(userLogin).get().getPurchasedTrainings()) {
            trainings.add(trainingRepository.findById(id).get());
        }
        return trainings;
    }

    /**
     * Add training
     * @param trainingDTO - training data
     * @param userLogin - username
     * @return training
     */
    @Override
    public Optional<Training> addTraining(TrainingDTO trainingDTO, String userLogin) {

        Training training = new Training(trainingDTO.getName(),
                trainingDTO.getDescription(), trainingDTO.getPrice(),
                userRepository.findByEmail(userLogin).get());
        List<Category> categories = new ArrayList<Category>();
        Category cat = categoryRepository.findById(trainingDTO.getCategory().getId()).get();
        categories.add(cat);
        training.setCategories(categories);
        log.info("Training successfully added.");
        return Optional.of(trainingRepository.save(training));
    }

    /**
     * Edit training
     * @param trainingId - training id
     * @param trainingDTO - training data
     * @param userLogin - username
     * @return training
     */
    @Override
    public Optional<Training> editTraining(UUID trainingId, TrainingDTO trainingDTO,
                                           String userLogin) {
        Optional<Training> training = trainingRepository.findById(trainingId);

        if (isTrainingExists(trainingId)) {
            if (isAuthorOrAdmin(userLogin, trainingId) && training.isPresent()) {
                training.get().setName(trainingDTO.getName());
                training.get().setDescription(trainingDTO.getDescription());
                training.get().setPrice(trainingDTO.getPrice());
                training.get().setEditDate(LocalDate.now());
                trainingRepository.save(training.get());

                log.info("Training was updated.");
                return training;
            }

            log.error("Impossible to edit training. User is not an author!");
            return Optional.empty();
        }

        log.error("Impossible to edit training. Training not found!");
        return Optional.empty();
    }

    /**
     * Find all trainings
     * @return list trainings
     */
    @Override
    public List<Training> findAll() {
        log.info("Trainings were received.");
        return trainingRepository.findAll();
    }

    /**
     * Find training by id
     * @param trainingId - training id
     * @return training
     */
    @Override
    public Optional<Training> findById(UUID trainingId) {
        log.info("Training was received.");
        if (isTrainingExists(trainingId)) {
            return trainingRepository.findById(trainingId);
        } else {
            log.warn("Training not found!");
            return Optional.empty();
        }
    }

    /**
     * Delete training
     * @param trainingId - training id
     * @param userLogin - username
     * @return training
     */
    @Override
    public Optional<Training> delete(UUID trainingId, String userLogin) {
        if (isTrainingExists(trainingId)) {

            Optional<Training> optionalTraining = trainingRepository.findById(trainingId);
            if (isAuthorOrAdmin(userLogin, trainingId)) {
                if (userRepository.findByEmail(userLogin).isPresent()) {

                    /* Удаление тренинга у пользователя. */
                    userRepository.findByEmail(userLogin).get().getTrainings()
                            .remove(trainingRepository.findById(trainingId).get());
                }

                trainingRepository.deleteById(trainingId);
                log.info("Training successfully deleted.");
                return optionalTraining;
            }
            log.warn("Training cannot to be deleted!");
            return Optional.empty();
        } else {
            log.warn("Training cannot to be deleted!");
            return Optional.empty();
        }
    }
}

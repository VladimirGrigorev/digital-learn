package com.netcracker.project.service.impl;

import com.netcracker.project.entity.Rating;
import com.netcracker.project.entity.Training;
import com.netcracker.project.entity.User;
import com.netcracker.project.entity.dto.RatingDTO;
import com.netcracker.project.entity.enums.RoleName;
import com.netcracker.project.repository.TrainingRepository;
import com.netcracker.project.repository.UserRepository;
import com.netcracker.project.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class RatingServiceImpl implements RatingService {

    /**
     * Beans for work with rating
     */
    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;

    public RatingServiceImpl(TrainingRepository trainingRepository, UserRepository userRepository) {
        this.trainingRepository = trainingRepository;
        this.userRepository = userRepository;
    }

    /**
     * Get rating
     * @param trainingId - training id
     * @return training value
     */
    @Override
    public double getRating(UUID trainingId) {
        Optional<Training> optionalTraining = trainingRepository.findById(trainingId);
        double rating = 0;
        if (optionalTraining.isPresent()) {
            for (Rating trainingRating : optionalTraining.get().getRating()) {
                rating += trainingRating.getRate();
            }
            rating /= optionalTraining.get().getRating().size();
            log.info("Rating was counted.");
            optionalTraining.get().setTrainingRate(rating);
            trainingRepository.save(optionalTraining.get());
            return rating;
        }

        log.warn("Impossible to count rating. Training not found!");
        return 0;
    }

    /**
     * Add rating
     * @param ratingDTO - rating data
     */
    @Override
    public void addRating(RatingDTO ratingDTO) {
        Optional<Training> optionalTraining =
                trainingRepository.findById(ratingDTO.getTrainingId());

        if (optionalTraining.isPresent() &&
                (ratingDTO.getRate() >= 1 && ratingDTO.getRate() <= 5)) {
            Rating rating = new Rating(ratingDTO.getUserId(), ratingDTO.getReview(),
                    ratingDTO.getRate(), optionalTraining.get());

            optionalTraining.get().getRating().add(rating);
            trainingRepository.save(optionalTraining.get());
            log.info("Rating was added.");
        }

        log.warn("Impossible to add rating. Training not found!");
    }

}

package com.netcracker.project.service;

import com.netcracker.project.entity.Rating;
import com.netcracker.project.entity.dto.RatingDTO;

import java.util.Optional;
import java.util.UUID;

public interface RatingService {

    /**
     * Get rating
     * @param trainingId - training id
     * @return training value
     */
    double getRating(UUID trainingId);

    /**
     * Add rating
     * @param ratingDTO - rating data
     */
    void addRating(RatingDTO ratingDTO);
}

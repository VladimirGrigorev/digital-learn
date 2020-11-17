package com.netcracker.project.entity.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.UUID;

/**
 * Rating DTO
 */
@Data
public class RatingDTO {
    /**
     * RatingDTO id
     */
    private @Setter(AccessLevel.PROTECTED) UUID Id;

    /**
     * RatingDTO rating id
     */
    private @Setter(AccessLevel.PROTECTED) UUID ratingId;

    /**
     * RatingDTO training id
     */
    private @Setter(AccessLevel.PROTECTED) UUID trainingId;

    /**
     * RatingDTO user id
     */
    private @Setter(AccessLevel.PROTECTED) UUID userId;

    /**
     * RatingDTO review
     */
    private String review;

    /**
     * RatingDTO rate
     */
    private double rate;
}

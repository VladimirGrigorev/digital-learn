package com.netcracker.project.controller;

import com.netcracker.project.entity.Rating;
import com.netcracker.project.entity.dto.RatingDTO;
import com.netcracker.project.service.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

/**
 * Controller for rating
 */
@RestController
@RequestMapping("/rating")
public class RatingController {

    /**
     * Rating service
     */
    private final RatingService ratingService;

    /**
     * Constructor
     */
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    /**
     * Gets rating
     * @param trainingId - training id
     * @return rating
     */
    @GetMapping("/{trainingId}")
    public double getRating(@PathVariable UUID trainingId) {
        return ratingService.getRating(trainingId);
    }

    /**
     * Add rating
     * @param ratingDTO - rating DTO
     * @return rating
     */
    @PostMapping("/add")
    public ResponseEntity<Rating> addRating(@RequestBody RatingDTO ratingDTO) {
        ratingService.addRating(ratingDTO);
        return ResponseEntity.ok().build();
    }
}

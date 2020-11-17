package com.netcracker.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

/**
 * Rating entity
 */
@JsonIgnoreProperties("training")
@Data @NoArgsConstructor
@Entity
public class Rating {

    /**
     * Rating id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Setter(AccessLevel.PROTECTED) UUID id;

    /**
     * Rating user id
     */
    private @Setter(AccessLevel.PROTECTED) UUID userId;

    /**
     * Rating training
     */
    @JsonIgnoreProperties({"user", "rating", "content"})
    @ManyToOne(fetch = FetchType.LAZY)
    private @ToString.Exclude Training training;

    /**
     * Rating value (1 <= rate <= 5)
     */
    private double rate;

    /**
     * Rating review
     */
    private String review;

    /**
     * Constructor
     */
    public Rating(UUID userId, String review, double rate, Training training) {
        this.training = training;
        this.userId = userId;
        this.review = review;
        this.rate = rate;
    }
}

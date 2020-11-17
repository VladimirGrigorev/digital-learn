package com.netcracker.project.entity.dto;

import com.netcracker.project.entity.Category;
import lombok.Data;

import java.util.UUID;

/**
 * Training DTO
 */
@Data
public class TrainingDTO {

    /**
     * TrainingDTO id
     */
    private UUID id;

    /**
     * TrainingDTO name
     */
    private String name;

    /**
     * TrainingDTO description
     */
    private String description;

    /**
     * TrainingDTO price
     */
    private int price;

    /**
     * TrainingDTO category
     */
    private Category category;
}

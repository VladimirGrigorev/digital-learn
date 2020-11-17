package com.netcracker.project.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * Category entity
 */
@Entity
@Data @NoArgsConstructor
@Table(name = "categories")
public class Category {

    /**
     * Category id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Setter(AccessLevel.PROTECTED)
    UUID id;

    /**
     * Category name
     */
    private String name;

    /**
     * Category description
     */
    private String description;

    /**
     * Category trainings
     */
    @ManyToMany
    @JoinTable(name="category_training",
            joinColumns=@JoinColumn(name="category_id"),
            inverseJoinColumns=@JoinColumn(name="training_id"))
    private @ToString.Exclude List<Training> trainings;
}

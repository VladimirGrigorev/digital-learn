package com.netcracker.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Training entity
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "trainings")
public class Training {

    /**
     * Training id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Setter(AccessLevel.PROTECTED) UUID id;

    /**
     * Training image url
     */
    private String imgUrl;

    /**
     * Training training rate
     */
    private double trainingRate;

    /**
     * Training name
     */
    private @NonNull String name;

    /**
     * Training description
     */
    private @NonNull String description;

    /**
     * Training price
     */
    private @NonNull int price;

    /**
     * Training creation date
     */
    private LocalDate creationDate;

    /**
     * Training edit date
     */
    private LocalDate editDate;

    /**
     * Training is delete
     */
    private Boolean isDelete = false;

    /**
     * Training rating
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<Rating> rating;

    /**
     * Training user
     */
    @JsonIgnoreProperties(value = {"trainings","cart"}, allowSetters = true)
    @ManyToOne (optional=false, fetch=FetchType.EAGER)
    @JoinColumn (name="user_id")
    @ToString.Exclude private User user;

    /**
     * Training categories
     */
    @ToString.Exclude
    @JsonIgnoreProperties(value = "trainings", allowSetters = true)
    @ManyToMany
    @JoinTable(name="category_training",
            joinColumns=@JoinColumn(name="training_id"),
            inverseJoinColumns=@JoinColumn(name="category_id"))
    private List<Category> categories;

    /**
     * Training content
     */
    @JsonIgnoreProperties(value = "training", allowSetters = true)
    @OneToMany (mappedBy="training", cascade = CascadeType.ALL)
    private List<File> content;

    /**
     * Constructor
     */
    public Training(@NonNull String name, @NonNull String description,
                    int price, User user) {
        this.creationDate = LocalDate.now();
        this.editDate = this.creationDate;
        this.rating = new ArrayList<>();
        this.description = description;
        this.price = price;
        this.user = user;
        this.name = name;
    }
}

package com.netcracker.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Order entity
 */
@Data
@NoArgsConstructor
@Entity @Table (name = "orders")
public class Order {

    /**
     * Order id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Setter(AccessLevel.PROTECTED) UUID id;

    /**
     * Order trainings
     */
    @ToString.Exclude
    @ManyToMany
    @JoinTable(name="order_training",
            joinColumns=@JoinColumn(name="order_id"),
            inverseJoinColumns=@JoinColumn(name="training_id"))
    private List<Training> trainings;

    /**
     * Order user
     */
    @JsonIgnoreProperties(value = {"order", "cart", "trainings"}, allowSetters = true)
    @ManyToOne (optional=false, fetch = FetchType.EAGER)
    @JoinColumn (name="user_id")
    private @NonNull @ToString.Exclude User user;

    /**
     * Order total
     */
    @Column(name = "order_total")
    private int orderTotal = 0;

    /**
     * Order date
     */
    private LocalDate orderDate;

    /**
     * Calculating order total
     */
    private void calcOrderTotal(List<Training> trainings) {
        for (Training training : trainings) {
            orderTotal += training.getPrice();
        }
    }

    /**
     * Constructor
     */
    public Order(List<Training> trainings, @NonNull User user) {
        this.trainings = trainings;
        this.user = user;
        this.orderDate = LocalDate.now();
        calcOrderTotal(trainings);
    }
}

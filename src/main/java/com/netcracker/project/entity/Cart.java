package com.netcracker.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * Cart entity
 */
@Data
@Entity
@NoArgsConstructor
@Table(name="carts")
public class Cart {

    /**
     * Cart id
     */
    @Id
    private UUID id;

    /**
     * Cart user
     */
    @ToString.Exclude
    @JsonIgnoreProperties(value = {"cart", "trainings"}, allowSetters = true)
    @OneToOne
    @MapsId
    private User user;

    /**
     * Cart trainings
     */
    @ToString.Exclude
    @ManyToMany
    @JoinTable(name="cart_training",
            joinColumns=@JoinColumn(name="cart_id"),
            inverseJoinColumns=@JoinColumn(name="training_id"))
    private List<Training> trainings;

    /**
     * Constructor
     * @param user - user
     */
    public Cart(User user) {
        this.user = user;
    }

    /**
     * Clear cart
     * @return cart
     */
    public Cart clear() {
        this.trainings.clear();
        return this;
    }
}

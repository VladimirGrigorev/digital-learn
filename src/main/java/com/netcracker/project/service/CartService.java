package com.netcracker.project.service;

import com.netcracker.project.entity.Cart;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartService {
    /**
     * Find all carts
     * @return list carts
     */
    List<Cart> findAll();

    /**
     * Find user cart
     * @param userLogin - username
     * @return cart or null
     */
    Optional<Cart> findUserCart(String userLogin);

    /**
     * Add training
     * @param trainingId - training id
     * @param name - username
     */
    void addTraining(UUID trainingId, String name);

    /**
     * Delete training
     * @param trainingId - training id
     * @param name - username
     */
    void deleteTraining(UUID trainingId, String name);

    /**
     * Get total price
     * @param cart - cart
     * @return total price
     */
    double getTotalPrice(Cart cart);

    /**
     * Clear cart
     * @param name - username
     */
    void clear(String name);
}

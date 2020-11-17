package com.netcracker.project.service;

import com.netcracker.project.entity.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {

    /**
     * Get order
     * @param userLogin - username
     * @return order
     */
    Optional<Order> getOrder(String userLogin);

    /**
     * Save order
     * @param order - order
     * @param userLogin - username
     * @return order
     */
    Optional<Order> saveOrder(Order order, String userLogin);

    /**
     * Delete order
     * @param orderId - order id
     * @param userLogin - username
     * @return order
     */
    Optional<Order> deleteOrder(UUID orderId, String userLogin);

    /**
     * Find all
     * @param userLogin - username
     * @return order
     */
    List<Order> findAll(String userLogin);

    /**
     * Find all user orders
     * @param userId - user id
     * @return order
     */
    List<Order> findAllUserOrders(UUID userId);
}

package com.netcracker.project.repository;

import com.netcracker.project.entity.Order;
import com.netcracker.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository for order
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    /**
     * Finds orders by user
     * @param user - user
     * @return list orders
     */
    List<Order> findByUser(User user);
}

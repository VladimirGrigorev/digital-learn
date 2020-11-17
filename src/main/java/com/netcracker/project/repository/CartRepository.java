package com.netcracker.project.repository;

import com.netcracker.project.entity.Cart;
import com.netcracker.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for carts
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
}


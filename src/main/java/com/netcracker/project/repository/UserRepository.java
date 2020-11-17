package com.netcracker.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.netcracker.project.entity.User;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository for users
 */
public interface UserRepository extends JpaRepository<User, UUID> {
    /**
     * Finds a user by username
     * @param name - username
     * @return user
     */
    User findByUsername(String name);

    /**
     * Finds users by email
     * @param email - user email
     * @return user
     */
    Optional<User> findByEmail(String email);

    /**
     * Existence of a user by email
     * @param email - user email
     * @return boolean
     */
    Boolean existsByEmail(String email);

    /**
     * Existence of a user by username
     * @param username - username
     * @return boolean
     */
    Boolean existsByUsername(String username);
}

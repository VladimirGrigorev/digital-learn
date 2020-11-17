package com.netcracker.project.service;

import com.netcracker.project.entity.Training;
import com.netcracker.project.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface UserService {

    /**
     * User register
     * @param user - user
     * @return user
     */
    User register(User user);

    /**
     * Find all users
     * @return list users
     */
    List<User> findAll();

    /**
     * Find user by username
     * @param username - username
     * @return user
     */
    User findByUsername(String username);

    /**
     * Delete user
     * @param id - user id
     */
    void delete(UUID id);
}
package com.netcracker.project.controller;

import com.netcracker.project.entity.User;
import com.netcracker.project.entity.dto.UserIdentityAvailability;
import com.netcracker.project.exception.ResourceNotFoundException;
import com.netcracker.project.repository.UserRepository;
import com.netcracker.project.security.CurrentUser;
import com.netcracker.project.security.UserPrincipal;
import com.netcracker.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller for user
 */
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * User service
     */
    private final UserService userService;

    /**
     * User repository
     */
    private final UserRepository userRepository;

    /**
     * Constructor
     */
    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    /**
     * Gets users
     * @return list users
     */
    @GetMapping
    public Iterable<User> getUsers(){
        return userService.findAll();
    }

    /**
     * Gets user by username
     * @param username - username
     * @return user
     */
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){
        return userService.findByUsername(username);
    }

    /**
     * Add user
     * @param user - user
     * @return message about result
     */
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    /**
     * Update user
     * @param user - user
     * @return message about result
     */
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    /**
     * Delete user
     * @param id - user id
     * @return message about result
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") UUID id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Gets current user
     * @param userPrincipal - user data
     * @return current user
     */
    @GetMapping("/me")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("UserInfo", "id", userPrincipal.getId()));
    }

    /**
     * Check username availability
     * @param username - username
     * @return UserIdentityAvailability
     */
    @GetMapping("/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        return new UserIdentityAvailability(!userRepository.existsByUsername(username));
    }

    /**
     * Check email availability
     * @param email - email
     * @return UserIdentityAvailability
     */
    @GetMapping("/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        return new UserIdentityAvailability(!userRepository.existsByEmail(email));
    }

    /**
     * Block or unblock user
     * @param email - email
     * @return user
     */
    @PostMapping("/blockOrUnblock")
    public ResponseEntity<User> blockOrUnblock(@RequestBody String email) {
        User user;
        if (userRepository.findByEmail(email).isPresent()) {
            user = userRepository.findByEmail(email).get();
            user.setIsblock(!user.getIsblock());
            userRepository.save(user);
        }
        return ResponseEntity.ok().build();
    }
}

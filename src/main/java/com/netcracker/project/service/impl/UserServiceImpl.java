package com.netcracker.project.service.impl;

import com.netcracker.project.entity.Cart;
import com.netcracker.project.entity.Role;
import com.netcracker.project.entity.Training;
import com.netcracker.project.entity.User;
import com.netcracker.project.entity.enums.AuthProvider;
import com.netcracker.project.entity.enums.RoleName;
import com.netcracker.project.repository.CartRepository;
import com.netcracker.project.repository.RoleRepository;
import com.netcracker.project.repository.UserRepository;
import com.netcracker.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    /**
     * Beans for work with user
     */
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    /**
     * Constructor
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * User register
     * @param user - user
     * @return user
     */
    public User register(User user) {
        Role roleUser = roleRepository.findByName(RoleName.ROLE_USER);
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(user.getPassword());
        user.setRoles(userRoles);
        user.setProvider(AuthProvider.local);

        User registeredUser = userRepository.save(user);

        userRepository.save(registeredUser);

        log.info("IN register - user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    /**
     * Delete user
     * @param id - user id
     */
    public void delete(UUID id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted", id);
    }

    /**
     * Load user by username
     */
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userId);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
    }

    /**
     * Get authority
     */
    private List getAuthority() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);

        log.info("users were received");
        return list;
    }

    /**
     * Find user by username
     * @param username - username
     * @return user
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

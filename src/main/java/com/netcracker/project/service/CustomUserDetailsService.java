package com.netcracker.project.service;

import com.netcracker.project.entity.User;
import com.netcracker.project.exception.ResourceNotFoundException;
import com.netcracker.project.repository.UserRepository;
import com.netcracker.project.security.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("UserInfo not found with email : " + email)
                );

        if (user.getIsblock()){
            throw new RuntimeException("Вы заблокированы");
        }

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("UserInfo", "id", id)
        );

        return UserPrincipal.create(user);
    }
}


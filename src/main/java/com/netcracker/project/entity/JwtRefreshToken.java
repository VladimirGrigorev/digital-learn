package com.netcracker.project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

/**
 * JwtRefreshToken entity
 */
@Entity
@Data @NoArgsConstructor
@Table(name = "refresh_tokens")
public class JwtRefreshToken {

    /**
     * JwtRefresh token
     */
    @Id
    private String token;

    /**
     * JwtRefreshToken user
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * JwtRefreshToken expiration date time
     */
    private Instant expirationDateTime;

    /**
     * Constructor
     */
    public JwtRefreshToken(String token) {
        this.token = token;
    }
}

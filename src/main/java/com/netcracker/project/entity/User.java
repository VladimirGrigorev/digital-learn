package com.netcracker.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.project.entity.enums.AuthProvider;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * User entity
 */
@Entity
@Data @NoArgsConstructor
@Table (name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User {

    /**
     * User id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Setter(AccessLevel.PROTECTED) UUID id;

    /**
     * User name
     */
    private @NonNull String username;

    /**
     * User email
     */
    @Email
    private @NonNull String email;

    /**
     * User image url
     */
    private String imageUrl;

    /**
     * User is block
     */
    private Boolean isblock = false;

    /**
     * User password
     */
    @JsonIgnore
    private @NonNull String password;

    /**
     * User provider
     */
    @Enumerated(EnumType.STRING)
    private @NotNull AuthProvider provider;

    /**
     * User provider id
     */
    private String providerId;

    /**
     * User progress
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<Progress> progress;

    /**
     * User roles
     */
    @ManyToMany
    @JoinTable(name="user_role",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id"))
    private Collection<Role> roles;

    /**
     * User trainings
     */
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    @OneToMany (mappedBy= "user", fetch=FetchType.EAGER)
    private List<Training> trainings;

    /**
     * User purchased trainings
     */
    @ElementCollection
    private List<UUID> purchasedTrainings;

    /**
     * User cart
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    /**
     * Constructor
     */
    public User(String email, String username, String password,
                List<Role> roles, AuthProvider provider, Cart cart) {
        purchasedTrainings = new ArrayList<>();
        this.trainings = new ArrayList<>();
        this.email = email;
        this.roles = roles;
        this.username = username;
        this.password = password;
        this.provider = provider;
        this.cart = cart;
    }
}

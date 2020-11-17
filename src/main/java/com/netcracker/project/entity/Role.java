package com.netcracker.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcracker.project.entity.enums.RoleName;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

/**
 * Role entity
 */
@Entity
@Data @NoArgsConstructor
@Table (name = "roles")
public class Role {

    /**
     * Role id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Setter(AccessLevel.PROTECTED) Integer id;

    /**
     * Role name
     */
    @Enumerated(EnumType.STRING)
    @NaturalId
    private @NonNull RoleName name;

    /**
     * Role users
     */
    @ToString.Exclude
    @JsonIgnore
    @ManyToMany
    @JoinTable(name="user_role",
            joinColumns=@JoinColumn(name="role_id"),
            inverseJoinColumns=@JoinColumn(name="user_id"))
    private List<User> users;

    /**
     * Constructor
     */
    public Role(RoleName name) {
        this.name = name;
    }
}

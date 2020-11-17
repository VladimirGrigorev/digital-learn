package com.netcracker.project.repository;

import com.netcracker.project.entity.Role;
import com.netcracker.project.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repository for roles
 */
public interface RoleRepository extends JpaRepository<Role, UUID> {

    /**
     * Finds a role by role name
     * @param name - role name
     * @return role
     */
    Role findByName(RoleName name);
}


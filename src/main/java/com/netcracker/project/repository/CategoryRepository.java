package com.netcracker.project.repository;

import com.netcracker.project.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository for categories
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
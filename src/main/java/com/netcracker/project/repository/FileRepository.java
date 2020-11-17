package com.netcracker.project.repository;

import com.netcracker.project.entity.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository for files
 */
@Repository
public interface FileRepository extends CrudRepository<File, UUID> {

}

package com.netcracker.project.service;

import com.netcracker.project.entity.File;
import com.netcracker.project.entity.dto.FileIdentifierDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FileStorageService {

    /**
     * Store file
     * @param file - file data
     * @param userLogin - username
     * @param trainingId - training id
     * @param description - training description
     * @param name - file name
     * @return file
     */
    Optional<File> storeFile(MultipartFile file, String userLogin,
                             UUID trainingId, String description,
                             String name);

    /**
     * Store multiple files
     * @param files - files data
     * @param userLogin - username
     * @param trainingId - training id
     * @param description - training description
     * @param name - file name
     * @return file
     */
    List<File> storeMultipleFiles(MultipartFile[] files, String userLogin,
                                  UUID trainingId, String description,
                                  String name);

    /**
     * Get file
     * @param fileIdentifierDTO - file identifier
     * @return file
     */
    Optional<File> getFile(FileIdentifierDTO fileIdentifierDTO);

    /**
     * Load file as resource
     * @param fileName - filename
     * @return resource
     */
    Resource loadFileAsResource(String fileName);

    /**
     * Download file
     * @param fileIdentifierDTO - file identifier
     * @return resource
     */
    ResponseEntity<Resource> downloadFile(FileIdentifierDTO fileIdentifierDTO);

    /**
     * List all files from training
     * @param trainingId - training id
     * @param userLogin - username
     * @return list files
     */
    Optional<List<File>> listAllfromTraining(UUID trainingId, String userLogin);

    /**
     * Get files by user
     * @param userLogin - username
     * @return list files
     */
    Optional<List<File>> listAll(String userLogin);

    /**
     * Delete file
     * @param id - file id
     */
    void delete(UUID id);
}

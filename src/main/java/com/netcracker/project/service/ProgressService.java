package com.netcracker.project.service;

import com.netcracker.project.entity.Progress;
import com.netcracker.project.entity.dto.FileIdentifierDTO;

import java.util.List;
import java.util.UUID;

public interface ProgressService {

    /**
     * Get progress
     * @param fileIdentifierDTO - file identifier
     * @param userLogin - username
     * @return progress value
     */
    double getProgress(FileIdentifierDTO fileIdentifierDTO,
                                   String userLogin);

    /**
     * Get all user progress
     * @param userLogin - username
     * @return list progress
     */
    List<Progress> getAllUserProgress(String userLogin);

    /**
     * Update progress
     * @param fileIdentifierDTO - file identifier
     * @param userLogin - username
     * @return progress value
     */
    double updateProgress(FileIdentifierDTO fileIdentifierDTO,
                            String userLogin);
}

package com.netcracker.project.controller;

import com.netcracker.project.entity.Progress;
import com.netcracker.project.entity.dto.FileIdentifierDTO;
import com.netcracker.project.service.ProgressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

/**
 * Controller for progress
 */
@RestController
@RequestMapping("/progress")
public class ProgressController {

    /**
     * Progress service
     */
    private final ProgressService progressService;

    /**
     * Constructor
     */
    ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    /**
     * Gets progress
     * @param fileIdentifierDTO - file identifier
     * @param principal - user
     * @return progress
     */
    @GetMapping
    public ResponseEntity<Double> getProgress(@RequestBody FileIdentifierDTO fileIdentifierDTO,
                                                Principal principal) {
        return ResponseEntity.ok(progressService
                .getProgress(fileIdentifierDTO, principal.getName()));
    }

    /**
     * Gets all user progress
     * @param principal - user
     * @return progress
     */
    @GetMapping("/all")
    public List<Progress> getAllUserProgress(Principal principal) {
        return progressService.getAllUserProgress(principal.getName());
    }

    /**
     * Update user progress
     * @param fileIdentifierDTO - file identifier
     * @param principal - user
     * @return progress
     */
    @PutMapping("/update")
    public ResponseEntity<Double> updateProgress(@RequestBody FileIdentifierDTO fileIdentifierDTO,
                                                   Principal principal) {
        return ResponseEntity.ok(progressService
                .updateProgress(fileIdentifierDTO, principal.getName()));
    }
}

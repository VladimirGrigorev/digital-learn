package com.netcracker.project.controller;

import com.netcracker.project.entity.File;
import com.netcracker.project.entity.dto.FileIdentifierDTO;
import com.netcracker.project.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

/**
 * Controller for file
 */
@Slf4j
@RestController
@RequestMapping("/files")
public class FileController {

    /**
     * File storage service
     */
    private final FileStorageService fileStorageService;

    /**
     * Constructor
     */
    public FileController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    /**
     * Upload file
     * @param file - file data
     * @param name - file name
     * @param description - file description
     * @param principal - creator
     * @param trainingId - training id
     * @return file entity or code error
     */
    @PostMapping("/uploadFile/{trainingId}")
    public ResponseEntity<File> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("name") String name,
                                           @RequestParam("description") String description, Principal principal,
                                           @PathVariable UUID trainingId) {

        return fileStorageService.storeFile(file, principal.getName(), trainingId, description, name)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());

    }

    /**
     * Upload multiple files
     * @param files - files data
     * @param name - files name
     * @param description - files description
     * @param principal - creator
     * @param trainingId - training id
     * @return list files
     */
    @PostMapping("/uploadMultipleFiles/{trainingId}")
    public List<File> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, @RequestParam("name") String name,
                                          @RequestParam("description") String description, Principal principal,
                                          @PathVariable UUID trainingId) {

        return fileStorageService.storeMultipleFiles(files, principal.getName(), trainingId, description, name);
    }

    /**
     * Download file
     * @param fileName - file name
     * @param request - http server request
     * @return download file
     */
    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    /**
     * Gets files by user
     * @param principal - user
     * @return list files
     */
    @GetMapping
    public ResponseEntity<List<File>> getFiles(Principal principal){

        return fileStorageService.listAll(principal.getName())
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    /**
     * Gets files from training
     * @param trainingId - training id
     * @param principal - user
     * @return list files
     */
    @GetMapping("/{trainingId}")
    public ResponseEntity<List<File>> getFilesFromTraining(@PathVariable UUID trainingId,
                                           Principal principal) {

        return fileStorageService.listAllfromTraining(trainingId, principal.getName())
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    /**
     * Delete file
     * @param id - file id
     * @return message about result
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<File> deleteFile(@PathVariable("id") UUID id) {
        fileStorageService.delete(id);
        return ResponseEntity.ok().build();
    }
}

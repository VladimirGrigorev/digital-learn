package com.netcracker.project.service.impl;

import com.netcracker.project.config.FileStorageProperties;
import com.netcracker.project.entity.File;
import com.netcracker.project.entity.Training;
import com.netcracker.project.entity.dto.FileIdentifierDTO;
import com.netcracker.project.entity.enums.ContentType;
import com.netcracker.project.entity.enums.RoleName;
import com.netcracker.project.exception.FileNotFoundException;
import com.netcracker.project.exception.FileStorageException;
import com.netcracker.project.repository.FileRepository;
import com.netcracker.project.repository.TrainingRepository;
import com.netcracker.project.repository.UserRepository;
import com.netcracker.project.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Slf4j
@Service
public class FileStorageServiceImpl implements FileStorageService {

    /**
     * Beans for work with files
     */
    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;
    private final FileRepository fileRepository;
    private final Path fileStorageLocation;

    /**
     * Constructor
     */
    public FileStorageServiceImpl(TrainingRepository trainingRepository, UserRepository userRepository,
                              FileRepository fileRepository, FileStorageProperties fileStorageProperties) {

        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException ex) {
            log.error("Could not create the directory {} where the uploaded files will be stored",
                    fileStorageProperties.getUploadDir());
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }

        this.trainingRepository = trainingRepository;
        this.userRepository = userRepository;
        this.fileRepository = fileRepository;
    }

    /**
     * Check is training exist
     * @param trainingId - training id
     * @return boolean
     */
    private boolean isTrainingExists(UUID trainingId) {
        return trainingRepository.findById(trainingId).isPresent();
    }

    /**
     * Check is author is admin
     * @param userLogin - username
     * @param trainingId - training id
     * @return boolean
     */
    private boolean isAuthorOrAdmin(String userLogin, UUID trainingId) {
        // If user author or admin - true
        return (userRepository.findByEmail(userLogin).get().getId().equals(
                trainingRepository.findById(trainingId).get().getUser().getId()) ||
                userRepository.findByEmail(userLogin).get().getRoles()
                        .contains(RoleName.ROLE_ADMIN));
    }

    /**
     * Download file
     * @param fileIdentifierDTO - file identifier
     * @return resource
     */
    @Override
    public ResponseEntity<Resource> downloadFile(FileIdentifierDTO fileIdentifierDTO) {
        if (this.getFile(fileIdentifierDTO).isPresent()) {
            File dbFile = this.getFile(fileIdentifierDTO).get();
            Resource resource = this.loadFileAsResource(dbFile.getName());

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(dbFile.getType().toString()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getName() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Store multiple files
     * @param files - files data
     * @param userLogin - username
     * @param trainingId - training id
     * @param description - training description
     * @param name - file name
     * @return file
     */
    @Override
    public List<File> storeMultipleFiles(MultipartFile[] files, String userLogin,
                                  UUID trainingId, String description, String name) {
        List<File> list = new ArrayList<>(files.length);
        for (MultipartFile file : files) {
            list.add(storeFile(file, userLogin, trainingId, description, name).get());
        }

        log.info("Multiple files were received.");
        return list;
    }

    /**
     * Store file
     * @param file - file data
     * @param userLogin - username
     * @param trainingId - training id
     * @param description - training description
     * @param name - file name
     * @return file
     */
    @Override
    public Optional<File> storeFile(MultipartFile file, String userLogin, UUID trainingId,
                                    String description, String name) {

        Optional<Training> training = this.trainingRepository.findById(trainingId);
        String fileName = StringUtils.cleanPath(
                Objects.requireNonNull(file.getOriginalFilename())
        );

        try {
            if (fileName.contains("..")) {
                log.error("Filename contains invalid path sequence {}", fileName);
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            if (isTrainingExists(trainingId)) {
                if (isAuthorOrAdmin(userLogin, trainingId)) {

                    File dbFile = new File(fileName, ContentType.getContentType(file),
                            file.getContentType(), this.fileStorageLocation.toString(),
                            training.get(), description, name);

                    Path targetLocation = this.fileStorageLocation.resolve(fileName);
                    Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                    log.info("The file was uploaded.");
                    return Optional.of(fileRepository.save(dbFile));
                } else {
                    log.error("Impossible to upload file. User not an author!");
                    return Optional.empty();
                }

            } else {
                log.error("Impossible to upload file. Training not found!");
                return Optional.empty();
            }
        } catch (IOException ex) {
            log.error("Could not store file {}", fileName);
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    /**
     * Get file
     * @param fileIdentifierDTO - file identifier
     * @return file
     */
    @Override
    public Optional<File> getFile(FileIdentifierDTO fileIdentifierDTO) {
        UUID trainingId = fileIdentifierDTO.getTrainingId();
        UUID fileId = fileIdentifierDTO.getFileId();
        if (isTrainingExists(trainingId)) {
            return Optional.of(fileRepository.findById(fileId).orElseThrow(()
                    -> new FileNotFoundException("File not found with id " + fileId)));
        }

        return Optional.empty();
    }

    /**
     * Load file as resource
     * @param fileName - filename
     * @return resource
     */
    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                log.info("The file was downloaded.");
                return resource;
            } else {
                log.error("File not found {}", fileName);
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            log.error("File not found {}", fileName);
            throw new FileNotFoundException("File not found " + fileName, ex);
        }
    }

    /**
     * Get files by user
     * @param userLogin - username
     * @return list files
     */
    @Override
    public Optional<List<File>> listAll(String userLogin) {
        if (userRepository.findByEmail(userLogin).get().getRoles()
                .contains(RoleName.ROLE_ADMIN)) {
            log.info("Files were received.");
            return Optional.of((List<File>) fileRepository.findAll());
        }

        return Optional.empty();
    }

    /**
     * List all files from training
     * @param trainingId - training id
     * @param userLogin - username
     * @return list files
     */
    @Override
    public Optional<List<File>> listAllfromTraining(UUID trainingId, String userLogin) {
        if (isTrainingExists(trainingId)) {
            if (isAuthorOrAdmin(userLogin, trainingId)) {
                log.info("Files from training were received.");
                return Optional.of(trainingRepository.findById(trainingId).get().getContent());
            }
        }

        return Optional.empty();
    }

    /**
     * Delete file
     * @param id - file id
     */
    @Override
    public void delete(UUID id) {
        try {
            File dbFile = getFile(id);
            Files.delete(Paths.get(dbFile.getPath() + "\\" + dbFile.getName()));
        } catch (IOException ex) {
            log.error("Could not delete the file");
        }
        finally {
            fileRepository.deleteById(id);
            log.info("the file {} was deleted", id);
        }
    }

    /**
     * Get file
     * @param fileId - file id
     * @return file
     */
    private File getFile(UUID fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found " + fileId));
    }
}
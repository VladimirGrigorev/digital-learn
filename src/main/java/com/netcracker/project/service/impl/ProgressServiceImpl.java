package com.netcracker.project.service.impl;

import com.netcracker.project.entity.File;
import com.netcracker.project.entity.Progress;
import com.netcracker.project.entity.Training;
import com.netcracker.project.entity.User;
import com.netcracker.project.entity.dto.FileIdentifierDTO;
import com.netcracker.project.entity.enums.ContentType;
import com.netcracker.project.entity.enums.RoleName;
import com.netcracker.project.repository.FileRepository;
import com.netcracker.project.repository.UserRepository;
import com.netcracker.project.service.ProgressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ProgressServiceImpl implements ProgressService {

    /**
     * Beans for work with progress
     */
    private final UserRepository userRepository;
    private final FileRepository fileRepository;

    /**
     * Constructor
     */
    public ProgressServiceImpl(UserRepository userRepository,
                               FileRepository fileRepository) {
        this.userRepository = userRepository;
        this.fileRepository = fileRepository;
    }

    /**
     * Check is content type correct
     * @param fileId - file id
     * @return boolean
     */
    private boolean isContentTypeCorrect(UUID fileId) {
        if (fileRepository.findById(fileId).isPresent()) {
            return fileRepository.findById(fileId).get()
                    .getContentType() == ContentType.ARTICLE
                    || fileRepository.findById(fileId).get()
                    .getContentType() == ContentType.VIDEO;
        }

        return false;
    }

    /**
     * Check user has training
     * @param fileIdentifierDTO - file identifier
     * @param user - user
     * @return boolean
     */
    private boolean hasTraining(FileIdentifierDTO fileIdentifierDTO, User user) {
        for (Training training : user.getTrainings()) {
            if (training.getId().toString().equals(
                    fileIdentifierDTO.getTrainingId().toString())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Update progress content
     * @param fileIdentifierDTO - file identifier
     * @param user - user
     * @return progress
     */
    private Progress updateProgressContent(FileIdentifierDTO fileIdentifierDTO,
                                       User user) {
        Progress progress = null;

        for (Progress userProgress : user.getProgress())  {
            if (userProgress.getTrainingId().toString().equals(
                    fileIdentifierDTO.getTrainingId().toString())) {
                progress = userProgress;
                break;
            }
        }

        /* Если прогресса нет, создаем новый. */
        if (progress == null) {
            progress = new Progress(user, fileIdentifierDTO.getTrainingId());
        }

        /* Поиск тренинга в котором изменился прогресс. */
        for (Training training : user.getTrainings()) {
            if (training.getId().toString().equals(
                    fileIdentifierDTO.getTrainingId().toString())) {

                /* Заполняем мапу значениями для определения прогресса. */
                for (File file : training.getContent()) {
                    if (isContentTypeCorrect(file.getId())) {

                        /* Если данный файл при подсчете прогресса раньше не
                        * учитывался, он записывается в мапу, если был,
                        * булево значение не меняется. */
                        progress.getContent().putIfAbsent(file.getId(), false);
                    }

                    /* Если id файла тренинга равно id файла из FileIdentifierDTO. */
                    if (file.getId().toString().equals(
                            fileIdentifierDTO.getFileId().toString())) {
                        /* Помечаем как выполненный. */
                        progress.getContent().put(file.getId(), true);
                    }
                }

                break;
            }
        }

        return progress;
    }

    /**
     * Update progress value
     * @param progress - progress
     */
    private void updateProgressValue(Progress progress) {
        double allContentNumber = 0;
        double doneContentNumber = 0;

        for (Map.Entry<UUID, Boolean> content : progress.getContent().entrySet()) {
            allContentNumber++;

            if (content.getValue()) {
                doneContentNumber++;
            }
        }

        if (allContentNumber == doneContentNumber) {
            progress.setProgress(1);
        } else {
            progress.setProgress((1 / allContentNumber) * doneContentNumber);
        }

    }

    /**
     * Check is owner or admin
     * @param userLogin - username
     * @param progressId - progress id
     * @return boolean
     */
    private boolean isOwnerOrAdmin(String userLogin, UUID progressId) {

        if (userRepository.findByEmail(userLogin).get().getRoles()
                .contains(RoleName.ROLE_ADMIN)) {
            return true;
        }

        for (Progress progress : userRepository.findByEmail(userLogin).get().getProgress())
        {
            if (progress.getId().toString().equals(progressId.toString())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Get progress
     * @param fileIdentifierDTO - file identifier
     * @param userLogin - username
     * @return progress value
     */
    @Override
    public double getProgress(FileIdentifierDTO fileIdentifierDTO,
                                          String userLogin) {
        if (userRepository.findByEmail(userLogin).isPresent()) {
            for (Progress progress : userRepository.findByEmail(userLogin).get().getProgress()) {
                if (progress.getTrainingId().toString().equals(
                        fileIdentifierDTO.getTrainingId().toString())) {
                    if (isOwnerOrAdmin(userLogin, progress.getId())) {
                        log.info("Progress received.");
                        return progress.getProgress();
                    }
                }
            }
            log.warn("Progress not found!");
            return 0;
        }
        log.warn("User not found!");
        return 0;
    }

    /**
     * Get all user progress
     * @param userLogin - username
     * @return list progress
     */
    @Override
    public List<Progress> getAllUserProgress(String userLogin) {
        if (userRepository.findByEmail(userLogin).isPresent()) {
            for (Progress progress : userRepository.findByEmail(userLogin).get().getProgress()) {
                updateProgressValue(progress);
            }
            log.info("Progress received.");
            return userRepository.findByEmail(userLogin).get().getProgress();
        }
        log.warn("User not found!");
        return new ArrayList<>();
    }

    /**
     * Update progress
     * @param fileIdentifierDTO - file identifier
     * @param userLogin - username
     * @return progress value
     */
    @Override
    public double updateProgress(FileIdentifierDTO fileIdentifierDTO,
                                             String userLogin) {

        Optional<User> optionalUser = userRepository.findByEmail(userLogin);
        Progress progress;

        if (optionalUser.isPresent()) {
            if (hasTraining(fileIdentifierDTO, optionalUser.get())
                    && isContentTypeCorrect(fileIdentifierDTO.getFileId())) {

                progress = updateProgressContent(fileIdentifierDTO, optionalUser.get());
                updateProgressValue(progress);
                for (Progress userProgress : optionalUser.get().getProgress()) {
                    if (userProgress.getId().toString().equals(progress.getId().toString())) {
                        userProgress = progress;
                        userRepository.save(optionalUser.get());
                        log.info("Progress was updated.");
                        return progress.getProgress();
                    }
                }
                optionalUser.get().getProgress().add(progress);
                userRepository.save(optionalUser.get());
                log.info("Progress was updated.");
                return progress.getProgress();
            }
        }
        log.warn("Impossible to update progress!");
        return 0;
    }
}

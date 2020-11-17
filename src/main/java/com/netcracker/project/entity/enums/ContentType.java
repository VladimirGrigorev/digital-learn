package com.netcracker.project.entity.enums;

import com.netcracker.project.entity.dto.ServiceConstants;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * Enumeration content type
 */
public enum ContentType {
    VIDEO,
    IMAGE,
    ARTICLE,
    OTHER;

    /**
     * Get content type
     * @param file - file
     * @return content type
     */
    public static ContentType getContentType(MultipartFile file) {
        String fileFormat = Objects.requireNonNull(file.getContentType()).toUpperCase();

        for (String imageType : ServiceConstants.image) {
            if (fileFormat.contains(imageType)) {
                return IMAGE;
            }
        }

        for (String videoType : ServiceConstants.video) {
            if (fileFormat.contains(videoType)) {
                return VIDEO;
            }
        }

        for (String articleType : ServiceConstants.article) {
            if (fileFormat.contains(articleType)) {
                return ARTICLE;
            }
        }

        return OTHER;
    }
}

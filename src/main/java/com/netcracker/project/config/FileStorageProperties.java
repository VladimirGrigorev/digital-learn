package com.netcracker.project.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * File properties
 */
@Data
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;
}

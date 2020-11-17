package com.netcracker.project.entity.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.UUID;

/**
 * File identifier DTO
 */
@Data
public class FileIdentifierDTO {
    /**
     * FileIdentifierDTO file id
     */
    private @Setter(AccessLevel.PROTECTED) UUID fileId;

    /**
     * FileIdentifierDTO training id
     */
    private @Setter(AccessLevel.PROTECTED) UUID trainingId;
}

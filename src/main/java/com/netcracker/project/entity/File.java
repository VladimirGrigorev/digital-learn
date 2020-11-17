package com.netcracker.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.project.entity.enums.ContentType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

/**
 * File entity
 */
@Entity
@Data @NoArgsConstructor
@Table(name = "files")
public class File {

    /**
     * File id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Setter(AccessLevel.PROTECTED) UUID id;

    /**
     * File training
     */
    @JsonIgnoreProperties(value = {"user", "content", "rating"}, allowSetters = true)
    @ManyToOne (optional = false, fetch = FetchType.EAGER)
    @JoinColumn (name="training_id")
    private @NonNull @ToString.Exclude Training training;

    /**
     * File upload date
     */
    private @NonNull LocalDate uploadDate;

    /**
     * File name
     */
    private @NonNull String name;

    /**
     * File content type (VIDEO, IMAGE, ARTICLE, OTHER)
     */
    @Enumerated(EnumType.STRING)
    private @NonNull ContentType contentType;

    /**
     * File type (file permission)
     */
    private String type;

    /**
     * File path
     */
    private @NonNull String path;

    /**
     * File description
     */
    private String description;

    /**
     * File content name
     */
    private String contentName;

    /**
     * Constructor
     */
    public File(String name, ContentType contentType, String type,
                String path, Training training, String description,
                String contentName) {
        this.uploadDate = LocalDate.now();
        this.contentType = contentType;
        this.training = training;
        this.type = type;
        this.name = name;
        this.path = path;
        this.description = description;
        this.contentName = contentName;
    }
}

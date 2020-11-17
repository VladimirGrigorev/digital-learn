package com.netcracker.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Progress entity
 */
@JsonIgnoreProperties("user")
@Data @NoArgsConstructor
@Entity
@Table(name = "progress")
public class Progress {

    /**
     * Progress id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Setter(AccessLevel.PROTECTED) UUID Id;

    /**
     * Progress training id
     */
    private @Setter(AccessLevel.PROTECTED) UUID trainingId;

    /**
     * Progress user
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private @ToString.Exclude User user;

    /**
     * Progress value (0 <= progress <= 1)
     */
    private double progress;

    /**
     * Progress content (UUID - file id, boolean - isDone)
     */
    @ElementCollection
    private Map<UUID, Boolean> content;

    /**
     * Constructor
     */
    public Progress(User user, UUID trainingId) {
        this.trainingId = trainingId;
        this.progress = 0;
        this.user = user;
        content = new HashMap<>();
    }
}

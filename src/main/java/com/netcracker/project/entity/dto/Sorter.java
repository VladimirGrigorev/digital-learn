package com.netcracker.project.entity.dto;

import com.netcracker.project.entity.enums.SorterDirection;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Sorter DTO
 */
@Data
public class Sorter {

    /**
     * Sorter direction
     */
    @Enumerated(EnumType.STRING)
    private SorterDirection direction;

    /**
     * Sorter property
     */
    private Object property;

    /**
     * Constructor
     */
    public Sorter(Object property, SorterDirection direction) {
        this.direction = direction;
        this.property = property;
    }
}

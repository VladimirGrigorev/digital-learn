package com.netcracker.project.entity;

import com.netcracker.project.entity.enums.FilterOperator;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

/**
 * Filter entity
 */
@Data
public class Filter {

    /**
     * Filter operator (EQUALS, IN)
     */
    @Enumerated(EnumType.STRING)
    private FilterOperator operator;

    /**
     * Filter property
     */
    private String property;

    /**
     * Filter value
     */
    private Object value;

    /**
     * Constructor
     */
    public Filter(List<Object> value, String property, FilterOperator operator) {
        this.operator = operator;
        this.property = property;
        this.value = value;
    }

    /**
     * Constructor
     */
    public Filter(Object value, String property, FilterOperator operator) {
        this.operator = operator;
        this.property = property;
        this.value = value;
    }
}

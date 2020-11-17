package com.netcracker.project.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Aggregator utils
 */
public class AggregatorUtils {

    /**
     * Gets aggregator property value
     * @param propertyName - property name
     * @param object - current object
     * @return properties
     */
    public static List<Object> getPropertyValue(String propertyName, Object object) {
        String[] dividedProperties = propertyName.split("\\.");
        Object currentObj = object;
        for (String property : dividedProperties) {
            if (currentObj == null) {
                break;
            } else if (property.equals("*")) {
                continue;
            } else if (currentObj instanceof List) {
                currentObj = getValueFromList((List) currentObj, property);
            } else {
                currentObj = getValue(currentObj, property);
            }
        }

        return currentObj == null ? Collections.emptyList() :
                currentObj instanceof List ?
                        (List) currentObj : List.of(currentObj);
    }

    /**
     * Gets aggregator value
     * @param obj - current object
     * @param property - property
     * @return value
     */
    private static Object getValue(Object obj, String property) {
        Object returnedObj = null;
        try {
            Field field = obj.getClass().getDeclaredField(property);
            field.setAccessible(true);
            returnedObj = field.get(obj);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return returnedObj;
    }

    /**
     * Gets aggregator value from list
     * @param list - list
     * @param property - property
     * @return value
     */
    private static Object getValueFromList(List<Object> list, String property) {
        List<Object> returnedObj = new ArrayList<>();
        list.forEach(obj -> returnedObj.add(getValue(obj, property)));
        return returnedObj;
    }
}
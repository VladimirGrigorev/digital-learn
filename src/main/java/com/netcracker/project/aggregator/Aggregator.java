package com.netcracker.project.aggregator;

import com.netcracker.project.entity.Filter;
import com.netcracker.project.entity.dto.Sorter;
import com.netcracker.project.entity.enums.FilterOperator;
import com.netcracker.project.entity.enums.SorterDirection;
import com.netcracker.project.util.AggregatorUtils;

import java.util.*;

/**
 * Aggregator for sorting, pagination and filtering
 * @param <T> - element type
 */
public class Aggregator<T> {

    /**
     * Gets result number for pagination
     */
    public double getResultsNumber(List<T> objectsToAggregate, Sorter[] sorters, Filter[] filters) {
        return this.aggregateObjectsForUI(objectsToAggregate, sorters, filters, null, null, null).size();
    }

    /**
     * Aggregate objects for UI
     */
    public List<T> aggregateObjectsForUI(List<T> objectsToAggregate, Sorter[] sorters, Filter[] filters,
                                         Integer page, Integer count, Integer size) {
        if (sorters == null && filters == null){
            return objectsToAggregate.subList((page - 1) * size, (page - 1) * size + count); // количество которое берем не всегда одинаково
        } else if (filters == null && page != null && count != null && size != null) {
              return sort(objectsToAggregate, sorters).subList((page - 1) * size, (page - 1) * size + count);
        } else {
            if (page == null || count == null || size == null) {
                if (filters == null) {
                    return sort(objectsToAggregate, sorters);
                }

                return sort(filter(objectsToAggregate, filters), sorters);
            } else {
                if (filter(objectsToAggregate, filters).size() - ((page - 1) * count) < size) {
                    return sort(filter(objectsToAggregate, filters), sorters)
                            .subList((page - 1) * count, objectsToAggregate.size());
                }
                return sort(filter(objectsToAggregate, filters), sorters)
                        .subList((page - 1) * count, page * count);
            }
        }
    }

    /**
     * Sorting
     */
    public List<T> sort(List<T> objectsToSort, Sorter[] sorters) {

        Object property;

        for (Sorter sorter : sorters) {

            // Итератор и цикл while потому что коллекция измениться во время итерирования
            Iterator<T> iterator = objectsToSort.iterator();
            while (iterator.hasNext()) {

                T object = iterator.next();
                property = AggregatorUtils.getPropertyValue(sorter.getProperty().toString(), object);
                assert property != null;

                if (sorter.getDirection() == SorterDirection.ASC) {
                    objectsToSort = quickSort(objectsToSort, sorter);
                    Collections.reverse(objectsToSort);
                    // Сортировка в прямом порядке
                } else if (sorter.getDirection() == SorterDirection.DESC) {
                    objectsToSort = quickSort(objectsToSort, sorter);
                    // Сортировка в обратном порядке
                }
            }
        }

        return objectsToSort;
    }

    /**
     * Filtering
     */
    public List<T> filter(List<T> objectsToFilter, Filter[] filters) {

        Object property;
        boolean isMatch = false;

        for (Filter filter : filters) {

            // Итератор и цикл while потому что коллекция может уменьшиться во время итерирования
            Iterator<T> iterator = objectsToFilter.iterator();
            while (iterator.hasNext()) {

                T object = iterator.next();
                property = AggregatorUtils.getPropertyValue(filter.getProperty(), object);
                assert property != null;

                if (filter.getOperator() == FilterOperator.EQUALS) {

                    for (Object propertyObject : (List) property) {
                        if (propertyObject.toString().equals(filter.getValue().toString())) {
                            isMatch = true;
                            break;
                        }
                    }

                } else if (filter.getOperator() == FilterOperator.IN) {

                    for (Object value : (Iterable) filter.getValue()) {
                        for (Object propertyObject : (List) property) {
                            if (propertyObject.toString().equals(value.toString())) {
                                isMatch = true;
                                break;
                            }
                        }
                    }
                }

                if (!isMatch) {
                    iterator.remove();
                }

                isMatch = false;
            }
        }

        return objectsToFilter;
    }

    /**
     * Quick sort
     */
    public List<T> quickSort(List<T> list, Sorter sorter) {
        return doSort(0, list.size() - 1, list, sorter);
    }

    /**
     * Method for quick sort
     */
    private List<T> doSort(int start, int end, List<T> array, Sorter sorter) {
        if (start >= end)
            return array;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (Double.parseDouble(AggregatorUtils.getPropertyValue((String) sorter.getProperty(), array.get(i)).get(0).toString())
                    <= Double.parseDouble(AggregatorUtils.getPropertyValue((String) sorter.getProperty(), array.get(cur)).get(0).toString()))) {
                i++;
            }
            while (j > cur && (Double.parseDouble(AggregatorUtils.getPropertyValue((String) sorter.getProperty(), array.get(cur)).get(0).toString())
                    <= Double.parseDouble(AggregatorUtils.getPropertyValue((String) sorter.getProperty(), array.get(j)).get(0).toString()))) {
                j--;
            }
            if (i < j) {
                T temp = array.get(i);
                array.set(i, array.get(j));
                array.set(j, temp);
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        doSort(start, cur, array, sorter);
        doSort(cur+1, end, array, sorter);
        return array;
    }
}

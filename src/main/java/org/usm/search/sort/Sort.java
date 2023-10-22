package org.usm.search.sort;

import java.util.Comparator;
import java.util.List;

public class Sort {
    public static <T> List<T> sortBy(List<T> sort, Comparator comparator) {
        sort.sort(comparator);
        return sort;
    }
}

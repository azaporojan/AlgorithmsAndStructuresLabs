package org.usm.search.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.function.Predicate;

@Getter
@Setter
public class PredicateComparator<T> {
    private T target;
    private Comparator<T> comparator;
    private Predicate<T> predicate;

}

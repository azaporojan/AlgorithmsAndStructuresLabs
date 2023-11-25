package org.usm.search.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Predicate;

public class Utility {

    public static LocalDateTime convertStringToLocalDateTime(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return LocalDateTime.parse(date, dateTimeFormatter);
    }

    public static <T> List<T> appendForward(List<T> to, List<T> from, int startIndex, Predicate<T> p) {
        int i = startIndex;
        while (i < from.size() && p.test(from.get(i))) {
            to.add(from.get(i));
            i++;
        }
        return to;
    }

    public static <T> List<T> appendBackward(List<T> to, List<T> from, int startIndex, Predicate<T> p) {
        int i = startIndex;
        while (i > 0 && p.test(from.get(i))) {
            to.add(from.get(i));
            i--;
        }
        return to;
    }

}

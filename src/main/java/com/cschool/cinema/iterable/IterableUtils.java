package com.cschool.cinema.iterable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class IterableUtils {
    public static <T> List<T> iterableToList(Iterable<T> iterable) {
        return StreamSupport
                .stream(iterable.spliterator(), false)
                .collect(Collectors.toList());

    }
}

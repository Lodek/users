package com.wipro.bartenders.users.util;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ControllerUtil {
    public static <T, U> List<U> mapIterable(Iterable<T> iterable, Function<T, U> f){
        Stream mapper = StreamSupport.stream(iterable.spliterator(), false);
        List <U> list = (List<U>) mapper.map(f).collect(Collectors.toList());
        return list;
    }

}

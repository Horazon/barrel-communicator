package pl.horazon.barrel.common.map;

import java.util.*;
import java.util.function.*;

public class ClassToConsumerMap {
    private final Map<Class<?>, Consumer<?>> map =
            new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> Consumer<T> put(Class<T> key, Consumer<T> c) {
        return (Consumer<T>) map.put(key, c);
    }

    @SuppressWarnings("unchecked")
    public <T> Consumer<T> get(Class<T> key) {
        return (Consumer<T>) map.get(key);
    }
}

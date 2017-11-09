package com.netcracker.trainee.utils;

import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public final class ReflectionUtils {

    public final static <T> Set<Class<? extends T>> findSubclassesForClass(Class<T> clazz, String basePackage,
                                                                           boolean hasAbstract) {
        if (clazz == null) {
            throw new IllegalArgumentException("Attempt to find subclasses for null reference on clazz parameter.");
        }

        basePackage = Optional.ofNullable(basePackage).orElse("");

        return new Reflections(basePackage)
                .getSubTypesOf(clazz)
                .stream()
                /*if hasAbstract == true, all classes are passed, else check abstract modifier*/
                .filter((c) -> (hasAbstract || !Modifier.isAbstract(c.getModifiers())))
                .collect(Collectors.toSet());
    }
}

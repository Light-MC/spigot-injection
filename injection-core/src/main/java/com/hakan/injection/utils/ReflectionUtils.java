package com.hakan.injection.utils;

import com.hakan.injection.annotations.Scanner;
import lombok.SneakyThrows;
import org.bukkit.plugin.Plugin;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;

/**
 * ReflectionUtils is a utility class
 * for Reflections library.
 */
@SuppressWarnings({"unchecked"})
public class ReflectionUtils {

    private static final Scanners[] SCANNERS = new Scanners[]{
            Scanners.SubTypes,
            Scanners.TypesAnnotated,
            Scanners.FieldsAnnotated,
            Scanners.MethodsAnnotated,
            Scanners.ConstructorsAnnotated,
    };

    /**
     * Creates a new Reflections instance.
     *
     * @param plugin plugin
     * @return reflections
     */
    public static @Nonnull Reflections createFrom(@Nonnull Plugin plugin) {
        return createFrom(plugin.getClass());
    }

    /**
     * Creates a new Reflections instance.
     *
     * @param instance instance
     * @return reflections
     */
    public static @Nonnull Reflections createFrom(@Nonnull Object instance) {
        return createFrom(instance.getClass());
    }

    /**
     * Creates a new Reflections instance.
     *
     * @param clazz clazz
     * @return reflections
     */
    public static @Nonnull Reflections createFrom(@Nonnull Class<?> clazz) {
        return createFrom(clazz.getAnnotation(Scanner.class));
    }

    /**
     * Creates a new Reflections instance.
     *
     * @param scanner scanner
     * @return reflections
     */
    public static @Nonnull Reflections createFrom(@Nonnull Scanner scanner) {
        return createFrom(scanner.value());
    }

    /**
     * Creates a new Reflections instance.
     *
     * @param path path
     * @return reflections
     */
    public static @Nonnull Reflections createFrom(@Nonnull String path) {
        if (path.isEmpty())
            throw new RuntimeException("scan path cannot be empty!");

        return new Reflections(path, SCANNERS);
    }

    /**
     * Gets field value from the instance.
     *
     * @param instance instance
     * @param field    field
     * @param <T>      type
     * @return value
     */
    @SneakyThrows
    public static @Nonnull <T> T getValue(@Nonnull Object instance,
                                          @Nonnull String field) {
        Field declaredField = instance.getClass().getDeclaredField(field);
        declaredField.setAccessible(true);

        return (T) declaredField.get(instance);
    }
}
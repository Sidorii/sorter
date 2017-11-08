package com.netcracker.trainee.config.impl;

import com.netcracker.trainee.config.SortersConfigurer;
import com.netcracker.trainee.sorters.Sorter;
import com.netcracker.trainee.sorters.SorterFactory;
import com.netcracker.trainee.utils.ReflectionUtils;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

/**
 * {@inheritDoc}
 * This {@link AutoDetectedSortersConfigurer class} configure Sorters by reflection.
 * It takes parent class and base package for searching, defines all subclasses of
 * parent class in base package and instantiate them by {@link SorterFactory} factory
 * class.
 *
 * @author Ivan Sidorenko
 * @version 1.0
 * @see SortersConfigurer
 * @see ReflectionUtils
 * @see Set
 * @since 1.0
 */
public class AutoDetectedSortersConfigurer implements SortersConfigurer {

    private String basePackage;
    private Class<? extends Sorter> parentClass;

    public AutoDetectedSortersConfigurer(Class<? extends Sorter> parentClass, String basePackage) {
        this.basePackage = basePackage;
        this.parentClass = parentClass;
    }

    /**
     * {@inheritDoc}
     * Configuring set sorters which is subclasses of <b>parentClass</b> in
     * <b>basePackage</b> and all subpackages.
     *
     * @throws NullPointerException if {@code parentClass} is null
     * @see ReflectionUtils#findSubclassesForClass(Class, String, boolean)
     * @see Set
     */
    @Override
    public Set<Sorter> configure() {
        Set<Sorter> sorters = new HashSet<>();

        ReflectionUtils.findSubclassesForClass(parentClass, basePackage, false)
                .stream()
                .filter((c) -> !Modifier.isAbstract(c.getModifiers()))
                .forEach((c) -> {
                    sorters.add(SorterFactory.getSorter(c));
                });
        return sorters;
    }
}

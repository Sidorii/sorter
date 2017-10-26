package com.netcracker.trainee.config.impl;

import com.netcracker.trainee.config.SortersConfigurer;
import com.netcracker.trainee.sorters.Sorter;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

public class AutoDetectedSortersConfigurer implements SortersConfigurer {

    private String basePackage;
    private Class<? extends Sorter> parentClass;

    public AutoDetectedSortersConfigurer(Class<? extends Sorter> parentClass, String basePackage) {
        this.basePackage = basePackage;
        this.parentClass = parentClass;
    }


    @Override
    public Set<Sorter> configure() {
        Set<Sorter> sorters = new HashSet<>();
        Reflections reflection = new Reflections(basePackage);


        reflection.getSubTypesOf(parentClass)
                .forEach((c) -> {
            try {
                if (!Modifier.isAbstract(c.getModifiers())) {
                    sorters.add(c.newInstance());
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException("Sorter instantiation exception. Notice that only public empty constructor" +
                                            " allowed for Sorter instantiation. Exception reason: " + e.getMessage());
            }
        });

        return sorters;
    }

}

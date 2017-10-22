package com.netcracker.trainee.fillers;

public @interface Filler {

    String name();

    String description() default "";
}

package com.netcracker.trainee.fillers;

public interface FillStrategy {

    int[] doFill();

    default String getName() {
        return "no-name";
    }
}

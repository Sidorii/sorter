package com.netcracker.trainee.fillers;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Random;


public class FillerArrayUtil {

    private FillerArrayUtil() {
    }


    @Filler(name = "sorted")
    public static int[] sortedArray(@Arg("length") int length) {
        checkPositive(length, "Array length can't be negative");
        return growingSortedSequence(length);
    }

    @Filler(name = "partSortedArray")
    public static int[] partSortedArray(@Arg("length") int length, @Arg("tile") int lastElement) {
        checkPositive(length,"Array length can't be negative");
        return ArrayUtils.add(growingSortedSequence(length), lastElement);
    }

    @Filler(name = "fallingSortedArray")
    public static int[] fallingSortedArray(@Arg("max") int max, @Arg("length") int length) {
        checkPositive(length,"Array length can't be negative");
        return fallingSortedSequence(length, max);
    }

    @Filler(name = "randomElementsArray")
    public static int[] randomElementsArray(@Arg("max") int length) {
        checkPositive(length,"Array length can't be negative");

        int array[] = new int[length];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }


    private static int[] growingSortedSequence(int length) {
        int[] array = new int[length];

        for (int i = 0; i < length; array[i] = i++) ;
        return array;
    }


    private static int[] fallingSortedSequence(int length, int max) {
        int[] array = new int[length];

        for (int i = length; i > 0; array[--i] = max - i) ;
        return array;
    }

    private static void checkPositive(int value, String message) {
        if (value < 0) {
            throw new IllegalArgumentException(message);
        }
    }
}

package com.netcracker.trainee.fillers;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Random;


public class FillerArrayUtil {

    private FillerArrayUtil() {
    }


    @Filler(name = "sorted")
    public static int[] sortedArray(@Arg("length") int length) {
        return growingSortedSequence(length);
    }

    @Filler(name = "partSortedArray")
    public static int[] partSortedArray(@Arg("length") int length, @Arg("tile") int lastElement) {
        return ArrayUtils.add(growingSortedSequence(length), lastElement);
    }

    @Filler(name = "fallingSortedArray")
    public static int[] fallingSortedArray(@Arg("max") int max) {
        int array[] = fallingSortedSequence(max);
        ArrayUtils.reverse(array);
        return array;
    }

    @Filler(name = "randomElementsArray")
    public static int[] randomElementsArray(@Arg("max") int length) {
        int array[] = new int[length];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }


    private static int[] growingSortedSequence(@Arg("length") int length) {
        int[] array = new int[length];

        for (int i = 0; i < length; array[i] = i++) ;
        return array;
    }


    private static int[] fallingSortedSequence(@Arg("length") int length) {
        int[] array = new int[length];

        for (int i = length; i > 0; array[--i] = i) ;
        return array;
    }
}

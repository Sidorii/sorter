package com.netcracker.trainee.fillers;

public class FillerUtil {

    public static int[] fill() {
        int[] arr = new int[100];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        return arr;
    }
}

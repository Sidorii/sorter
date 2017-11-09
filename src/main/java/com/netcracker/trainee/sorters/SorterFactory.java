package com.netcracker.trainee.sorters;

import com.netcracker.trainee.sorters.impl.*;

import java.util.NoSuchElementException;

public class SorterFactory {


    public static final <T extends Sorter> T getSorter(Class<T> type) {

        if (FallingBubbleSorter.class.equals(type)) {
            return (T) FallingBubbleSorter.getInstance();
        }

        if (GrowingBubbleSorter.class.equals(type)) {
            return (T) GrowingBubbleSorter.getInstance();
        }

        if (InsertSorter.class.equals(type)) {
            return (T) InsertSorter.getInstance();
        }

        if (MergeSorter.class.equals(type)) {
            return (T) MergeSorter.getInstance();
        }

        if (QuickSorter.class.equals(type)) {
            return (T) QuickSorter.getInstance();
        }

        if (SystemSorter.class.equals(type)) {
            return (T) SystemSorter.getInstance();
        }

        throw new NoSuchElementException("No such instance for class " + type + " found.");
    }
}

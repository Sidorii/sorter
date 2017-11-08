package com.netcracker.trainee.sorters;

import com.netcracker.trainee.sorters.impl.*;

import java.util.NoSuchElementException;

public class SorterFactory {


    public static final <T extends Sorter> T getSorter(Class<T> type) {

        if (type.equals(FallingBubbleSorter.class)) {
            return (T) FallingBubbleSorter.getInstance();
        }

        if (type.equals(GrowingBubbleSorter.class)) {
            return (T) GrowingBubbleSorter.getInstance();
        }

        if (type.equals(InsertSorter.class)) {
            return (T) InsertSorter.getInstance();
        }

        if (type.equals(MergeSorter.class)) {
            return (T) MergeSorter.getInstance();
        }

        if (type.equals(QuickSorter.class)) {
            return (T) QuickSorter.getInstance();
        }

        if (type.equals(SystemSorter.class)) {
            return (T) SystemSorter.getInstance();
        }

        throw new NoSuchElementException("No such instance for class " + type + " found.");
    }
}

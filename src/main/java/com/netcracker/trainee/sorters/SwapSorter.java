package com.netcracker.trainee.sorters;

/**
 * Abstract class {@link SwapSorter} is child from {@link Sorter} and used as parent or interface for sorting algorithms that swap elements durring sorting processing.
 *
 * @author Ivan Sidorenko
 * @version 1.0
 * @see Sorter
 * @since 1.1
 */
abstract public class SwapSorter extends Sorter {

    private int buff;

    /**
     * Swapping position of two elements in array. Result of this method is array of int where element with index1 will be on index2 position as well as index2 will be on index1 position.
     *
     * @param array  the array of ints which contains elements with indexes {@code index1} and {@code index2} that'll be swapped.
     * @param index1 the index of some element in array that should swap to {@code index2} position.
     * @param index2 the index of some element in array that should swap to {@code index1} position.
     *
     * @throws NullPointerException      'll be thrown if input array is null.
     * @throws IndexOutOfBoundsException 'll be thrown if any of indexes in method args is out of bounds array.
     */
    protected void swap(int[] array, int index1, int index2) {
        checkIndexInBounds(array, index1);
        checkIndexInBounds(array, index2);

        buff = array[index1];
        array[index1] = array[index2];
        array[index2] = buff;
    }

    /**
     * Check if index is in array bounds
     *
     * @throws IndexOutOfBoundsException will be thrown if index is out of array bounds.
     * */
    protected final void checkIndexInBounds(int[] array, int index) {
        int size = array.length;

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds while processing swap operation in array with size = " + size +
                    ". Index with value = " + index + " is out of range.");
        }
    }
}

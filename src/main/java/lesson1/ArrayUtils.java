package lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ArrayUtils {

    static <T> void swap(T[] array, int firstIndex, int secondIndex) {

        if (firstIndex != secondIndex) {
            try {
                T temp = array[firstIndex];
                array[firstIndex] = array[secondIndex];
                array[secondIndex] = temp;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Indexes out of array's bounds");
            }
        }

    }

    static <T> ArrayList<T> asArrayList(T[] array) {
        return new ArrayList<T>(Arrays.asList(array));
    }





}

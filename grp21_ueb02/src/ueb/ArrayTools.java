package ueb;

/**
 * Tools for using an array.
 * @author Mo, klk, Anton Schmitter, Joshua-Scott Schöttke, Gruppe 21
 */
public class ArrayTools {
    /**
     * determines if a certain {@code value} is containing in a
     * given {@code array} and returns the position of the first occurrence.
     *
     * @param array the given array
     * @param value the value to look for
     * @return the position of the first occurrence of the value, if not contained -1
     * @throws IllegalArgumentException if the given array is {@code null}
     */
    public static int containsAt(int[] array, int value) {
        int counter = -1;
        if (array == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value && counter == -1) {
                counter = i;
            }

        }
        return counter;
    }
    /**
     * deletes in a given array the element at position {@code idx}
     *
     * @param array the given array
     * @param idx the position to delete at
     * @return a new array not containing the {@code idx}-th value
     * @throws IllegalArgumentException if the given array is {@code null} or the {@code idx} is invalid
     */
    public static int[] deleteElementAt(int[] array, int idx) {
        if (array == null || idx < 0 || idx >= array.length) {
            throw new IllegalArgumentException();
        } else {
            int[] arr2 = new int[array.length - 1];
            System.arraycopy(array, 0, arr2, 0, idx);
            System.arraycopy(array, idx + 1, arr2, idx, arr2.length - idx);

            return arr2;
        }
    }

    /**
     * inserts in a given array the element at position {@code idx}
     *
     * @param array the given array
     * @param idx the position to insert at
     * @param value the inserting value
     * @return a new array containing at {@code idx} the given {@code value},
     * null if invalid params
     * @throws IllegalArgumentException if the array is {@code null} or the {@code idx} is invalid
     */
    public static int[] insertElementAt(int[] array, int idx, int value) {
        if (array == null || idx < 0 || idx > array.length) {
            throw new IllegalArgumentException();
        } else {
            int j = 0;
            int [] arr2 = new int[array.length+1];
            if (array.length == 0){
                arr2[0] = value;
            } else {
                for (int i = 0; i <= arr2.length-1; i++) {
                    if (i == idx) {
                        arr2[i] = value;
                    } else {
                        if (i < idx) {
                            arr2[i] = array[i];
                            j++;
                        } else {
                            arr2[i] = array[j];
                            j++;
                        }
                    }
                }
            }
            return arr2;
        }
    }
    
    /**
     * Gets the length of the longest array in given array.
     * @param array 2-dimensional array to look in
     * @return length of longest array in given array, -1 if only {@code null}-values contained
     * @throws IllegalArgumentException if array is {@code null} or length of array is 0
     */
    public static int getLengthOfLongestArray(int[][] array) {
        if ((array == null) || (array.length == 0)) {
            throw new IllegalArgumentException();
        }
        int length = 0;
        boolean noValues = true;
        for (int[] ints : array) {
            noValues = noValues && ints == null;
            if (ints != null && ints.length >= length) {
                length = ints.length;
            }
        }
        if (noValues) {
            return -1;
        } else {
            return length;
        }
    }
}

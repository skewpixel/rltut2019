package com.skewpixel.rltut2019.util;

public class ArrayUtils {

    /*
     * initialize a smaller piece of the array and use the System.arraycopy
     * call to fill in the rest of the array in an expanding binary fashion
     * https://stackoverflow.com/questions/9128737/fastest-way-to-set-all-values-of-an-array
     */
    public static void arrayfill(boolean[] array, boolean value) {
        int len = array.length;

        if (len > 0){
            array[0] = value;
        }

        //Value of i will be [1, 2, 4, 8, 16, 32, ..., len]
        for (int i = 1; i < len; i += i) {
            System.arraycopy(array, 0, array, i, ((len - i) < i) ? (len - i) : i);
        }
    }
}

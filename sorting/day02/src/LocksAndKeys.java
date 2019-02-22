public class LocksAndKeys {

    private static void swap(char[] A, int i, int j) {
        char t = A[i];
        A[i] = A[j];
        A[j] = t;
    }


    static char[][] locksAndKeys(char[] locks, char[] keys) {
        // TODO: Return a 2d char array, where result[0] is the sorted locks, and result[1] is the sorted keys
        quickSort(locks, keys, 0, locks.length-1);
        // start quicksorting from the last element
        char[][] result = new char[2][];
        result[0] = locks;
        result[1] = keys;
        for (int x = 0; x<result.length; x++)
            System.out.println(result[x]);

        return result;
    }


    public static int partition(char[] array, int lo, int hi, char p) {
        int l = lo ;
        for (int x = lo; x < hi; x++) {

            // if element is smaller than pivot from other array
            if (array[x] < p) {
                // switch the element where the pointer is in the subarray
                // increment the pointer
                swap(array, l++, x);
            }
            else if(array[x] == p){
                // if the element is equal to the pivot
                // switch the last element of the subarray with the element you looked before
                swap(array, x--, hi);
            }
        }

        // lastly, swap pivot with the last element swapped
        swap(array, l, hi);

        // return the index of lastly swapped element
        return l;
    }

    public static void quickSort(char[] locks, char[] keys, int lo, int hi) {
        if (lo < hi) {
            // last character of the locks is the "pivot" of keys array
            int p = partition(locks, lo, hi, keys[hi]);

            // compare the last element (initially), the pivot with all
            // elements in the subarray of keys
            partition(keys, lo, hi, locks[p]);

            // quicksort recursively
            quickSort(locks, keys, lo, p - 1);
            quickSort(locks, keys, p + 1, hi);
        }
    }

}


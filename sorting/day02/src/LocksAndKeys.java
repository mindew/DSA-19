public class LocksAndKeys {

    private static void swap(char[] A, int i, int j) {
        char t = A[i];
        A[i] = A[j];
        A[j] = t;
    }

    public void quickSort(int[] a, int lo, int hi) {
        if (lo < hi) {
            int p = partition(a, lo, hi);
            quickSort(a, lo, p-1);
            quickSort(a, p+1, hi);
        }
    }

    public int partition(int[] array, int lo, int hi) {
        int p = array[lo];
        int l = lo+1;
        for (int x = lo+1; x<=hi; x++) {
            if (array[x]<p) {
                swap(array, l, x);
                l++;
            }
        }
        swap(array, lo, l-1);
        return l-1;
    }

    static char[][] locksAndKeys(char[] locks, char[] keys) {
        // TODO: Return a 2d char array, where result[0] is the sorted locks, and result[1] is the sorted keys

        for (int x=0; x<locks.length; x++) {
            char pivot = locks[x];
            for (int y=0; x<keys.length; y++) {
                if (keys[y] < pivot) {
                    swap()
                }

            }
        }






        char[][] result = new char[2][];
        result[0] = locks;
        result[1] = keys;
        return result;
    }
}





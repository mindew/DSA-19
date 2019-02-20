
public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     * <p>
     * TODO
     * Best-case runtime: O(n lgn)
     * Worst-case runtime: O(n lgn)
     * Average-case runtime: O(n lgn)
     * <p>
     * Space-complexity: O(n)
     */


    @Override
    public int[] sort(int[] array) {
        // TODO

        if (array.length > 1 && array.length <= INSERTION_THRESHOLD) {
            InsertionSort InsertionSortion = new InsertionSort();
            InsertionSortion.sort(array);
            return array;
        }

        if (array.length <= 1) {
            return array;
        }

        else {
            int mididx = (int)Math.floor(array.length / 2);
            int arrayl[] = new int[mididx];
            System.arraycopy(array, 0, arrayl, 0, mididx);
            int arrayr[] = new int[array.length - mididx];
            System.arraycopy(array, mididx, arrayr, 0, array.length - mididx);

            arrayl = sort(arrayl);
            arrayr = sort(arrayr);

            int[] temp = merge(arrayl, arrayr);

            return temp;

            //System.out.println(array.length);
        }


    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {
        // TODO
        int result[] = new int[a.length + b.length];

        int pointerA = 0;
        int pointerB = 0;
        int resultidx = 0;

        while (pointerA < a.length && pointerB < b.length) {
            if (a[pointerA] > b[pointerB]) {
                result[resultidx] = b[pointerB];
                pointerB++;
                resultidx++;
            } else {
                result[resultidx] = a[pointerA];
                pointerA++;
                resultidx++;
            }

        }

        while (pointerA < a.length)
        {
            result[resultidx] = a[pointerA];
            pointerA++;
            resultidx++;
        }

        while (pointerB < b.length)
        {
            result[resultidx] = b[pointerB];
            pointerB++;
            resultidx++;
        }

        return result;
    }

}

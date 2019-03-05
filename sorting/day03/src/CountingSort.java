public class CountingSort {

    /**
     * Use counting sort to sort non-negative integer array A.
     * Runtime: O(n+k)
     * <p>
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        int k = 0;

        // finding max element k from A
        for (int i = 0; i < A.length; i++) {
            if (A[i] > k) {
                k = A[i];
            }
        }

        // creating a counting array ranging from 0 to maxelem
        int counting[] = new int[k+1];

        // count how many elements are there in an array
        for (int i = 0; i<A.length; i++) {
            int elemvalue = A[i];
            counting[elemvalue]++;
        }

        // pointer for A
        int pointer = 0;

        for (int i = 0; i<counting.length; i++) {
            int counter = counting[i];

            while (counter--!=0) {
                A[pointer++] = i;
            }
        }
    }

}

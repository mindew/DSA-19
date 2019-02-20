
public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     *
     * TODO
     * Best-case runtime: O(N)
     * Worst-case runtime: O(N^2)
     * Average-case runtime: O(N^2)
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        for (int i=1; i<array.length; i++) {
            int currentidx = i;
            while (currentidx >0 && array[currentidx-1] > array[currentidx]){
                int temp = array[currentidx-1];
                array[currentidx-1] = array[currentidx];
                array[currentidx] = temp;
                currentidx--;
            }

        }

        return array;
    }
}

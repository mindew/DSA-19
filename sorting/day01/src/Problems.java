import java.util.*;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }

        System.out.print("expected: ");
        System.out.println(Arrays.toString(out));
        return out;
    }


    /**
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] inputStream) {
        PriorityQueue<Integer> pqmax = maxPQ();
        // first element is the largest element
        // reversed order so that max to min
        PriorityQueue<Integer> pqmin = minPQ();
        // first element is the smallest element
        // min to max

        double[] med = new double[inputStream.length];
        System.out.print("actual: ");

        for (int x = 0; x < inputStream.length; x++) {
            if (x == 0) {
                med[x] = (double) inputStream[x];
                pqmax.offer(inputStream[x]);
            }

            if (x != 0) {
                if (inputStream[x] < med[x - 1]) {
                    // input is smaller than median
                    pqmax.offer(inputStream[x]);
                } else {
                    // input is bigger than median
                    pqmin.offer(inputStream[x]);
                }

                // if the size are equal for minq and maxq
                if (pqmax.size() != pqmin.size()) {
                    // if the size of both qs are not equal
                    // pqmin is less than pqmax
                    if (pqmin.size() < pqmax.size()) {
                        pqmin.offer(pqmax.poll());
                    } else if (pqmin.size() - pqmax.size() >= 2) {
                        pqmax.offer(pqmin.poll());
                    }
                }


                if (pqmax.size() == pqmin.size()) {
                    med[x] = (double) (pqmax.peek() + pqmin.peek()) / 2.0;
                } else {
                    med[x] = (double) (pqmin.peek());
                }
            }
        }
        System.out.println(Arrays.toString(med));
        return med;
    }

}

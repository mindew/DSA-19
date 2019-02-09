package your_code;

import java.util.LinkedList;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {

    private LinkedList<Integer> ll = new LinkedList<>();

    public void enqueue(int item) {
        int index = 0;

        if (ll.isEmpty()) {
            ll.add(item);
        }
        else {
            for (int x = 0; x <= ll.size()-1; x++)
            {
            if (item < ll.get(index))
            {
                index = index + 1;
            }

            }
        }

        ll.add(index, item);
    }

    /**
     * Return and remove the largest item on the queue.s
     */
    public int dequeueMax() {

        int maxval = ll.getFirst();
        ll.removeFirst();

        return maxval;

    }

}
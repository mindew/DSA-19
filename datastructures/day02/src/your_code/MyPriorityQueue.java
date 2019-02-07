package your_code;

import java.util.LinkedList;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {

    private LinkedList<Integer> ll;
    int size = 0;

    public void enqueue(int item) {

        ll.addLast(item);
        size++;
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {

        int maxval = 0;
        int current = 0;
        int maxvalind = 0;

        for (int x = 0; x < size; x++)
        {
            current = ll.get(x);
            if (maxval < current) {
                maxval = current;
                maxvalind = x;
            }
            else
                continue;
        }

        ll.remove(maxvalind);
        size--;

        return maxval;

    }

}
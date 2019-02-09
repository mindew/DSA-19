package your_code;

import ADTs.StackADT;

import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll = new LinkedList<>();
    private LinkedList<Integer> maxll = new LinkedList<>();

    public MyStack() {
        ll = new LinkedList<>();

    }

    @Override
    public void push(Integer e) {
        ll.addFirst(e);
        if (maxll.isEmpty())
            maxll.addFirst(e);
        else if (e > maxll.peek()) {
                maxll.addFirst(e);
        }

    }

    @Override
    public Integer pop() {
        Integer pop = ll.removeFirst();
        if (pop == maxll.peek())
            maxll.removeFirst();
        return pop;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer maxElement() {
        // TODO
        return maxll.peek();

    }
}

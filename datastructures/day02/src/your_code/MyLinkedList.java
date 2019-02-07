package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        if (size == 0){
            head = new Node(c);
            tail = head;
            size = size+1;
        }
        else {
            Node newtail = new Node(c, tail, null);
            tail.next = newtail;
            tail = newtail;
            size = size+1;
        }
    }

    public void addFirst(Chicken c) {
        if (size == 0){
            head = new Node(c);
            tail = head;
            size = size+1;
        }
        else {
            Node newhead = new Node(c, null, head);
            head.prev = newhead;
            head = newhead;
            size = size+1;
        }
    }

    public Chicken get(int index) {
        Node current = head;
        for (int x=0; x<index; x++)
            current = current.next;
        return current.val;
    }

    public Chicken remove(int index) {

        if (index == 0)
            return removeFirst();
        else if (index == size-1)
            return removeLast();
        else {
            Node current = head;
        for (int x=0; x<index; x++)
            current = current.next;

        current.prev.next = current.next;
        current.next.prev = current.prev;
        size = size -1;

        return current.val;
        }
    }

    public Chicken removeFirst() {
        if (size == 0) {
            return null;
        }
        else if (size == 1) {
            Chicken headdead = head.val;
            head = null;
            tail = null;
            size = size - 1;
            return headdead;
        }
        else {
            Node current = head;
            head = current.next;
            Chicken dead = current.val;
            current = null;

            size = size - 1;
            return dead;
        }
    }

    public Chicken removeLast() {
        if (size == 0) {
            return null;
        }
        else if (size == 1) {
            Chicken headdead = head.val;
            head = null;
            tail = null;
            size = size - 1;
            return headdead;
        }
        else {
            Node current = tail;
            tail = current.prev;
            Chicken dead = current.val;
            current = null;

            size = size - 1;
            return dead;
        }
    }
}
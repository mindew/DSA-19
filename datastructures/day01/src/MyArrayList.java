public class MyArrayList {
    private Cow[] elems;
    private int size = 0;

    // TODO: Runtime: O(1)
    public MyArrayList() {
        elems = new Cow[10];
    }

    // TODO: Runtime: O(1)
    public MyArrayList(int capacity) {
        elems = new Cow[capacity];
    }

    // TODO: Runtime: O(1)*
    public void add(Cow c) {
        grow();
        elems[size] = c;
        size = size + 1;
    }

    // TODO: Runtime: O(1)
    public int size() {
        return size;
    }

    // TODO: Runtime: O(1)
    public Cow get(int index) {
        if (index < size) {
            return elems[index];
        } else {
            throw new IndexOutOfBoundsException();
        }

    }

    // TODO: Runtime: O(N)
    public Cow remove(int index) {
        if (index < size) {

            Cow result = elems[index];
            for (int x = index + 1; x < size; x++)
                elems[x - 1] = elems[x];

            size = size - 1;
            shrink();

            return result;

        } else {
            throw new IndexOutOfBoundsException();
        }

    }

    // TODO: Runtime: O(N)
    public void add(int index, Cow c) {
        if (index <= size) {
            grow();
            for (int x = index; x <= size; x++)
                elems[x + 1] = elems[x];
            elems[index] = c;
            size = size + 1;
        } else {
            throw new IndexOutOfBoundsException();
        }

    }

    public void shrink() {
        if (elems.length / 4 > size) {
            Cow[] result = new Cow[size / 2];
            System.arraycopy(elems, 0, result, 0, size);
            elems = result;
        }
    }

    public void grow() {
        if (elems.length == size) {
            Cow[] result = new Cow[size * 2];
            System.arraycopy(elems, 0, result, 0, size);
            elems = result;
        }
    }
}
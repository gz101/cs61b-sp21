package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private int getModIndex(int i) {
        int index = i % items.length;
        if (index < 0) {
            index += items.length;
        }
        return index;
    }

    private void resize(int capacity) {
        T[] newArr = (T[]) new Object[capacity];

        // TODO: handle smaller array resize and set nextFirst and nextLast
        if (capacity > items.length) {
            // copy front over
            int frontStart = getModIndex(nextFirst + 1);
            System.arraycopy(
                    items, frontStart, newArr, capacity - (size - frontStart), size - frontStart
            );

            // copy end over
            System.arraycopy(items, 0, newArr, 0, frontStart);

            nextFirst = capacity - (size - frontStart) - 1;
            nextLast = frontStart;
        } else {

        }

        items = newArr;
    }

    @Override
    public void addFirst(T item) {
        if (size() == items.length) {
            resize(size() * 2);
        }

        items[getModIndex(nextFirst)] = item;
        nextFirst--;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size() == items.length) {
            resize(size() * 2);
        }

        items[getModIndex(nextLast)] = item;
        nextLast++;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int start = nextFirst + 1;
        System.out.print("[");
        for (int i = start; i < start + size - 1; i++) {
            System.out.print(items[getModIndex(i)] + ", ");
        }
        System.out.println(items[getModIndex(start + size - 1)] + "]");
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        // TODO: resize if too small

        T val = items[getModIndex(nextFirst + 1)];
        nextFirst++;
        size--;
        return val;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        // TODO: resize if too small

        T val = items[getModIndex(nextLast - 1)];
        nextLast--;
        size--;
        return val;
    }

    @Override
    public T get(int index) {
        int start = getModIndex(nextFirst + 1);
        return items[getModIndex(start + index)];
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private void printBaseDeque() {
        System.out.print("[");
        for (int i = 0; i < items.length - 1; i++) {
            System.out.print(items[i] + ", ");
        }
        System.out.println(items[items.length - 1] + "]");
    }
}

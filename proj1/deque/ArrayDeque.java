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

    private void resize(int capacity) {
        T[] newArr = (T[]) new Object[capacity];

        // TODO: handle smaller array resize and set nextFirst and nextLast
        if (capacity > items.length) {
            // copy front over
            System.arraycopy(
                    items, (size / 2) - 1, newArr, (3 * capacity / 4) - 1, size
            );

            // copy end over
            System.arraycopy(items, 0, newArr, 0, (size / 2) - 2);

            nextFirst = (3 * capacity / 4) - 1;
            nextLast = (size / 2) - 1;
        } else {

        }

        items = newArr;
    }

    @Override
    public void addFirst(T item) {
        if (size() == items.length) {
            resize(size() * 2);
        }

        items[nextFirst % items.length] = item;
        nextFirst--;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size() == items.length) {
            resize(size() * 2);
        }

        items[nextLast % items.length] = item;
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
            System.out.print(items[i % items.length] + ", ");
        }
        System.out.println(items[(start + size - 1) % items.length] + "]");
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        // TODO: resize if too small

        T val = items[(nextFirst + 1) % items.length];
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

        T val = items[(nextLast - 1) % items.length];
        nextLast--;
        size--;
        return val;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
